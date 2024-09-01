package gitshop.gitjpashop.controller;

import gitshop.gitjpashop.domain.Address;
import gitshop.gitjpashop.domain.Member;
import gitshop.gitjpashop.domain.MemberRole;
import gitshop.gitjpashop.domain.MemberStatus;
import gitshop.gitjpashop.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    // 회원 등록 페이지
    @GetMapping("member/register")
    public String createForm(Model model){
        model.addAttribute("memberForm", new MemberForm());
        return "member/memberForm";
    }

    // 회원 등록
    @PostMapping("member/register")
    public String create(@Valid MemberForm form, BindingResult result) {
                        // bindingResult는 검증 객체 뒤에 와야 된다.

        if(result.hasErrors()) { // 바인딩 실패시
            return "member/memberForm";
        }

        // 주소 입력
        Address address = new Address(form.getCity(), form.getStreet(), form.getZipcode());

        // 멤버 정보 입력
        Member member = new Member(form.getEmail(), form.getPassword(), form.getName(), address, MemberRole.USER, MemberStatus.ING);

        // 멤버 저장
        memberService.join(member);

        // 저장된 멤버 조회
//        Member findMember = memberService.findOne(member.getId());

        // 메인으로
        return "redirect:/";
    }

    // 로그인
    @GetMapping("/member/login")
    public String login(Model model){

        return "member/loginForm";
    }


}
