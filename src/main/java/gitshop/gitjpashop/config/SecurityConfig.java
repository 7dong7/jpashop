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
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(auth -> auth
                        .disable()) // 사이트 보안
                .authorizeHttpRequests(authz -> authz // authorizeHttpRequests 메서드를 사용하여 각 URL 패턴에 대해 접근권한을 설정할 수 있다.
                        .requestMatchers("/","/login","member/register").permitAll()
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/item/**").hasRole("USER")
                        .anyRequest().authenticated() // 그 외의 모든 요청은 인증 필요
                )
                .formLogin(form -> form
                        .loginPage("/login")    // 로그인 기본 경로 (GET)
                        .loginProcessingUrl("/login")   // POST
//                        .usernameParameter("username") // 로그인 할때 사용되는 아이디
//                        .passwordParameter("password") // 로그인 할때 사용되는 비밀번호
                        .defaultSuccessUrl("/") // 로그인 성공 후 이동페이지 ("/", true)로 설정하면 url 요청페이지가 아니라 "/" 경로로 고정된다.
//                        .failureUrl("/login")   // 로그인 실패시 이동 경로
                        .permitAll() // 누구나 접근 가능
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")   // 로그아웃
                        .logoutSuccessUrl("/login") // 로그아웃 이후 이동 페이지
                );

        return http.build();
    }
}