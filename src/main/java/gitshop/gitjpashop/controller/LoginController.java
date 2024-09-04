package gitshop.gitjpashop.controller;

import gitshop.gitjpashop.controller.form.LoginForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class LoginController {

    // --------- 로그인 ---------
    @GetMapping("/login")
    public String loginPage(Model model){
        model.addAttribute("loginForm", new LoginForm());
        return "loginForm";
    }

  /*  @PostMapping("/login")
    public String login(@Valid LoginForm form, BindingResult result, Model model){

        if(result.hasErrors()) { // 바인딩 실패시
            return "/loginForm";
        }

        System.out.println("form.getEmail() = " + form.getEmail());
        System.out.println("form.getPassword() = " + form.getPassword());

        Member loginMember = memberService.findLoginMember(form);

        if(loginMember == null){
            System.out.println("로그인 사용자 없음");
        }

        return "redirect:/";
    }*/

    // 로그아웃

}
