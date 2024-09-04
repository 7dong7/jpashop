package gitshop.gitjpashop.service;

import gitshop.gitjpashop.domain.Member;
import gitshop.gitjpashop.dto.CustomUserDetails;
import gitshop.gitjpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    /**
     *  String username은 사용자가 로그인할 때 사용한 아이디이다.
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Member> member = memberRepository.findByEmail(email);

        if(member.isPresent()) {

            return new CustomUserDetails(member.get());
        }

        return null;
    }
}
