package gitshop.gitjpashop.controller;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MemberForm {

    @NotEmpty(message = "이메일(아이디)을 입력해주세요.")
    @Size(min = 5, max = 30)
    private String email;

    @NotEmpty(message = "비밀번호를 입력해주세요.")
    @Size(min = 8, max = 15)
    private String password;

    @NotEmpty(message = "회원이름을 입력해주세요.")
    @Size(min = 2, max = 10)
    private String name;

    private String city;
    private String street;
    private String zipcode;
}
