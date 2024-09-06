package gitshop.gitjpashop.controller.form;

import gitshop.gitjpashop.dto.ValidationGroups;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MemberForm {

    @NotEmpty(message = "이메일을 입력해주세요.", groups = ValidationGroups.NotEmptyCheckGroup.class)
    @Size(min = 8, max = 30, message = "아이디는 8글자 이상 30글자 이하여야 합니다.", groups = ValidationGroups.SizeCheckGroup.class)
    private String email;

    @NotEmpty(message = "비밀번호를 입력해주세요.", groups = ValidationGroups.NotEmptyCheckGroup.class)
    @Size(min = 8, max = 15, message = "비밀번호는 5글자 이상 30글자 이하여야 합니다.", groups = ValidationGroups.SizeCheckGroup.class)
    private String password;

    @NotEmpty(message = "회원이름을 입력해주세요.", groups = ValidationGroups.NotEmptyCheckGroup.class)
    @Size(min = 2, max = 10, message = "이름은 2글자 이상 10글자 이하여야 합니다.", groups = ValidationGroups.SizeCheckGroup.class)
    private String name;

    private String city;
    private String street;
    private String zipcode;
}
