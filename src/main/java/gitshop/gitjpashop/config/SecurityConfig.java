/*
package gitshop.gitjpashop.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

*/
/**
 *  @Configuration 는 해당 클래스를 spring의 설정 클래스로 정의함
 *  @Configuration 는 Spring 컨테이너에 의해서 관리됨.
 *
 *  @EnableWebSecurity 는 spring security의 핵심 어노테이션
 *  @EnableWebSecurity 는 spring boot에서 웹 보안을 활성화하는 역할
 *
 *  과거 SecurityConfig는 'WebSecurityConfigurerAdapter'를 extends해서 작성하였으나
 *  최신버전에서는 'SecurityFilterChain'의 사용을 권장함
 *//*

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests((authorizeRequests) -> // 인증, 인가 설정
                        authorizeRequests
                                .requestMatchers("/item/register")
                                .authenticated()
                                .anyRequest()
                                .permitAll()
                )
                .formLogin((formLogin) -> // 폼 기반 로그인 설정
                        formLogin
                                .loginPage("/member/login") // get
                                .loginProcessingUrl("/member/login") // post -> 스프링 시큐리티가 로그인 프로세스 진행
                                .defaultSuccessUrl("/")
                )
                .build();
    }
}
*/
