package com.deliveryservice.api.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

@Slf4j
@RequiredArgsConstructor
@Component
public class AuthorizationInterceptor implements HandlerInterceptor {

    //사전에 검증
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("Authorization Interceptor Uri : {}", request.getRequestURI());

        //web, chorme 의 경우 get,post Options == pass
        if (HttpMethod.OPTIONS.matches(request.getMethod()))return true;

        //js,html png resourec를  요청하는 경우 =pass
        if (handler instanceof ResourceHttpRequestHandler)return true;

        //TODO:header 검증

        return true; //일단 통과 처리
    }
}
