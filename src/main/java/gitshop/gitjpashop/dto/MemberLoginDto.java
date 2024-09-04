package gitshop.gitjpashop.dto;

import gitshop.gitjpashop.domain.MemberRole;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberLoginDto {

    private Long id;
    private String name;
    private String password;
    private String email;
    private MemberRole role;

}
