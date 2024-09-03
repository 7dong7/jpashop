package gitshop.gitjpashop.controller.form;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class LoginForm {

    @NotEmpty(message = "이메일을 입력해주세요.")
    @Size(min = 5, max = 30)
    private String email;

    @NotEmpty(message = "비밀번호를 입력해주세요.")
    @Size(min = 8, max = 15)
    private String password;

}
