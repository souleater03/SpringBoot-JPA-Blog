
package com.example.MyWeb.demo.config;

import com.example.MyWeb.demo.config.auth.PrincipalDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration //빈등록
@EnableWebSecurity //시큐리티 라는 필터 추가, 이미 시큐리티가 활성화되어있지만 어떤 특수한 설정은 여기서 하겠다 라는 의미.
@EnableMethodSecurity(prePostEnabled = true) //특정 주소로 접근하면 권한 및 인증을 미리 체크하겠다는것.
public class SecurityConfig {
    @Autowired
    private PrincipalDetailService principalDetailService;
    @Bean
    public BCryptPasswordEncoder encodePWD(){
        return new BCryptPasswordEncoder();
    }

    // 시큐리티가 대신 로그인 해주는데 password를 가로채기 할건데..
    // 해당 password가 뭘로 해쉬가 되어 회원가입이 됬는지 알아야
    // 같은 해쉬로 암호화 해서 DB에 있는 해쉬랑 비교 할 수 있다는것. 그래서 알려줘야함(로그인시)

    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(principalDetailService).passwordEncoder(encodePWD());
    }
    //아마 해당 userDetaileService 메쏘드는 principalDetailService 값을 가지고 id,pwd를 찾을것이다. 그리고나서 인코드해주는것


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable() //csrf 토큰 비활성화 (테스트에만 걸어두자, 백신프로그램 같은거임)
                .authorizeHttpRequests(authorizeHttpRequests ->
                {
                    try {
                        authorizeHttpRequests//리퀘스트가 들어오면
                                .requestMatchers("/","/auth/**","/js/**","/css/**","/image/**")
                                .permitAll()
                                .anyRequest()
                                .authenticated()
                                .and()
                                .formLogin()
                                .loginPage("/auth/loginForm")
                                .loginProcessingUrl("/auth/loginProc") //form action="/auth/loginProc" api를 만드는대신 스프링 시큐리티가 로그인 요청을 가로채서 대신 로그인을 해준다.
                                .defaultSuccessUrl("/"); //성공시url

                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });

        return http.build();
    }
}