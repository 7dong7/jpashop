package gitshop.gitjpashop.controller;

import gitshop.gitjpashop.controller.form.MemberForm;
import gitshop.gitjpashop.domain.Address;
import gitshop.gitjpashop.domain.Member;
import gitshop.gitjpashop.domain.MemberRole;
import gitshop.gitjpashop.domain.MemberStatus;
import gitshop.gitjpashop.dto.ValidationSequence;
import gitshop.gitjpashop.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;

    // --------- 회원 등록 ---------
    @GetMapping("member/register")
    public String createForm(Model model){
        model.addAttribute("memberForm", new MemberForm());
        return "member/memberForm";
    }

    @PostMapping("member/register")
    public String create(@Validated(ValidationSequence.class) MemberForm memberForm, BindingResult result) {
                                            // bindingResult는 검증 객체 뒤에 와야 된다.
        System.out.println("들어옴");
        if(result.hasErrors()) { // 바인딩 실패시
            return "member/memberForm";
        }
        
        // 주소 입력
        Address address = new Address(memberForm.getCity(), memberForm.getStreet(), memberForm.getZipcode());

        // password 암호화
        String encodePassword = passwordEncoder.encode(memberForm.getPassword());

        // 멤버 정보 입력
        Member member = new Member(memberForm.getEmail(), encodePassword, memberForm.getName(), address, MemberRole.ROLE_USER, MemberStatus.ING);

        // 멤버 저장
        memberService.join(member);

        // 저장된 멤버 조회
//        Member findMember = memberService.findOne(member.getId());

        // 회원 등록 후 로그인 페이지
        return "redirect:/login";
    }


}
