package gitshop.gitjpashop.controller;

import gitshop.gitjpashop.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("member/register")
    public String createForm(Model model){
        model.addAttribute("memberForm", new MemberForm());
        return "member/memberForm";
    }

}
