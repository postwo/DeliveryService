package org.delivery.storeadmin.config.security;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.List;

@Configuration
public class SecurityConfig {

    private List<String> SWAGGER = List.of(
            "/swagger-ui.html",
            "/swagger-ui/**",
            "/v3/api-docs/**"
    );


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth

                        .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()  // 리소스에 대한 요청 허용

                        .requestMatchers(SWAGGER.toArray(new String[0])).permitAll()  // Swagger에 대한 요청 허용

                        .requestMatchers("/open-api/**").permitAll()  // /open-api 하위 모든 경로에 대한 요청 허용

                        .anyRequest().authenticated()  // 그 외의 모든 요청에 대해서는 인증 요구
                )
                .formLogin(form -> form
//                        .loginPage("/login")  // 로그인 페이지 경로 (기본 경로를 사용하지 않고 커스텀 페이지 사용 시 지정)
                                .defaultSuccessUrl("/main", true)  // 로그인 성공 시 이동할 URL 지정
                );

        return httpSecurity.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        // hash 로 암호화
        return new BCryptPasswordEncoder();
    }

}
