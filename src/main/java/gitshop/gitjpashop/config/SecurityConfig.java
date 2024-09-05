package gitshop.gitjpashop.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 *  @Configuration 는 해당 클래스를 spring의 설정 클래스로 정의함
 *  @Configuration 는 Spring 컨테이너에 의해서 관리됨.
 *
 *  @EnableWebSecurity 는 spring security의 핵심 어노테이션
 *  @EnableWebSecurity 는 spring boot에서 웹 보안을 활성화하는 역할
 *
 *  과거 SecurityConfig는 'WebSecurityConfigurerAdapter'를 extends해서 작성하였으나
 *  스프링 시큘리티 5.x 버전에서는 'SecurityFilterChain'의 사용을 권장함
 *  웹 보안 설정을 더 유연하게 작성할 수 있음
 */

@Configuration  // 스프링 설정 클래스임을 나타냅니다.
@EnableWebSecurity  // 스프링 시큐리티를 활성화합니다.
public class SecurityConfig{

    // password 암호화
    @Bean
    public PasswordEncoder passwordEncoder() {
        // BCryptPasswordEncoder는 비밀번호 암호화 도구
        // SHA-256를 구현하거나 BCryptPasswordEncoder를 사용
        return new BCryptPasswordEncoder();
    }

    // 스프링 시큐리티 css, js, image 등의 정적 리소스 완전 예외처리
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web.ignoring()
                .requestMatchers(PathRequest
                        .toStaticResources()
                        .atCommonLocations());
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authz -> authz // authorizeHttpRequests 메서드를 사용하여 각 URL 패턴에 대해 접근권한을 설정할 수 있다.
                        .requestMatchers("/","/login","member/register").permitAll()
                        .requestMatchers("/admin/**").hasRole("ADMIN") // /admin 주소에는 ADMIN 권한을 가진 사용자만 접근 가능
                        .requestMatchers("/item/**").hasRole("USER") // /item 주소에는 USER 권한을 가진 사용자만 접근 가능
                        .anyRequest().permitAll()
                )
                .formLogin(form -> form
                        .loginPage("/login")    // 작성한 로그인 페이지 
                        .defaultSuccessUrl("/") // 로그인 성공 후 이동페이지
                        .permitAll() // 누구나 접근 가능
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")   // 로그아웃
                        .logoutSuccessUrl("/login") // 로그아웃 이후 이동 페이지
                        .permitAll() // 로그아웃 허용
                );

        return http.build();
    }

    /*@Bean
    public UserDetailsService userDetailsService(){
        UserDetails user = User
                .withUsername("email")
                .password("password")
                .roles("USER") // 유저 역할 부여
                .build();
        return  new InMemoryUserDetailsManager(user);
    }*/
}