package gitshop.gitjpashop.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

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
                .requestMatchers("/css/**","/js/**","/images/**","/fonts/**");
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authz -> authz // authorizeHttpRequests 메서드를 사용하여 각 URL 패턴에 대해 접근권한을 설정할 수 있다.
                        .requestMatchers("/","/login","member/register").permitAll()
                        .requestMatchers("/admin/**").hasRole("ADMIN") // /admin 주소에는 ADMIN 권한을 가진 사용자만 접근 가능
                        .requestMatchers("/user/**").hasRole("USER") // /user 주소에는 USER 권한을 가진 사용자만 접근 가능

                )
                .formLogin(form -> form
                        .loginPage("/login")    // 작성한 로그인 페이지 
                        .defaultSuccessUrl("/", true) // 로그인 성공 후 이동페이지
                        .permitAll() // 누구나 접근 가능
                )
                .logout(logout -> logout
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
