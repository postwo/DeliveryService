package com.deliveryservice.api.config.web;

import com.deliveryservice.api.interceptor.AuthorizationInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    private final AuthorizationInterceptor authorizationInterceptor;

    private List<String> OPEN_API = List.of("/open-api/**");

    private List<String> DEFAULT_EXCLUDE = List.of("/","favicon.ico","/error");

    private List<String> SWAGGER =List.of("/swagger-ui.html","/swagger-ui","/v3/api-docs/**");

    @Override
    public void addInterceptors(InterceptorRegistry registry) { //세가지 주소 빼고는 다검증을 할거다
        registry.addInterceptor(authorizationInterceptor)
                .excludePathPatterns(OPEN_API) //인증 x 로그인이 안되어도 사용가능
                .excludePathPatterns(DEFAULT_EXCLUDE)
                .excludePathPatterns(SWAGGER)
        ;
    }
}
