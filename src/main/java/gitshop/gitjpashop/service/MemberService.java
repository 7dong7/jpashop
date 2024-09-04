package gitshop.gitjpashop.service;

import gitshop.gitjpashop.domain.Member;
import gitshop.gitjpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;
    
    // 회원 등록
    @Transactional
    public void join(Member member) {
        validateDuplicateMember(member);
        memberRepository.save(member);
    }

    // 회원 조회
    public Member findOne(Long id) {
        return memberRepository.findOne(id);
    }
    





    // 중복 검사 로직
    private void validateDuplicateMember(Member member) {
        Optional<Member> findByEmailMembers = memberRepository.findByEmail(member.getEmail());
        if (findByEmailMembers.isPresent()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
        List<Member> findByNameMembers = memberRepository.findByName(member.getName());
        if (!findByNameMembers.isEmpty()) {
            throw new IllegalStateException("사용중인 이름입니다.");
        }
    }
}
