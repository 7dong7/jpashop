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


/*
@Override
protected void configure(HttpSecurity http) throws Exception {
    http
            .httpBasic().disable() // rest api 이므로 기본설정 사용안함. 기본설정은 비인증시 로그인폼 화면으로 리다이렉트 된다.
            .csrf().disable() // rest api이므로 csrf 보안이 필요없으므로 disable처리.
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // jwt token으로 인증하므로 세션은 필요없으므로 생성안함.
            .and()
            .authorizeRequests() // 다음 리퀘스트에 대한 사용권한 체크
            .antMatchers("/**").permitAll(); // 가입 및 인증 주소는 누구나 접근가능
    //.antMatchers("/v1/**").permitAll() // 가입 및 인증 주소는 누구나 접근가능
    //.anyRequest().hasRole("USER"); // 그외 나머지 요청은 모두 인증된 회원만 접근 가능
}

@Override
public void configure(WebSecurity web) throws Exception {
}*/
