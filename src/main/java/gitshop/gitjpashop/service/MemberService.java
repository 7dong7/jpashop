package gitshop.gitjpashop.service;

import gitshop.gitjpashop.domain.Member;
import gitshop.gitjpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public Long join(Member member) {

        memberRepository.save(member);
        return member.getMember_id();
    }


}
