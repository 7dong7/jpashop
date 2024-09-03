package gitshop.gitjpashop.service;

import gitshop.gitjpashop.controller.form.LoginForm;
import gitshop.gitjpashop.domain.Member;
import gitshop.gitjpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;
    
    // 회원 등록
    @Transactional
    public void join(Member member) {
        memberRepository.save(member);
    }

    // 회원 조회
    public Member findOne(Long id) {
        return memberRepository.findOne(id);
    }
    
    // 로그인
    public Member findLoginMember(LoginForm form) {
        return memberRepository.findLoginMember(form);
    }

}
