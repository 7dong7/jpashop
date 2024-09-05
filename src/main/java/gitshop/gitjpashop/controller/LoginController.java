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
}
