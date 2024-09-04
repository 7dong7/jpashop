package gitshop.gitjpashop.dto;


import gitshop.gitjpashop.domain.Member;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class CustomUserDetails implements UserDetails {

    private Member member;

    public CustomUserDetails(Member member) {
        this.member = member;
    }

    // 사용자의 권한을 리턴해 주는 메소드
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        Collection<GrantedAuthority> collection = new ArrayList<GrantedAuthority>();

        collection.add(new GrantedAuthority() {

            @Override
            public String getAuthority() {

                return member.getRole().toString();
            }
        });

        return collection;
    }

    // 사용자의 비밀번호
    @Override
    public String getPassword() {
        return member.getPassword();
    }

    // 사용자의 아이디
    @Override
    public String getUsername() {
        return member.getEmail();
    }

    // ---- 특정한 상황에 대해서 이야기 함 ---- 계정의 잠금 여부  (테이블에 관련 컬럼을 추가해야됨)
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // 아이디 만료 여부 - 설정해야됨 -
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
