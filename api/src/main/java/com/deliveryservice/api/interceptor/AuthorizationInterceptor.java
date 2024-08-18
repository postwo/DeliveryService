package com.deliveryservice.api.interceptor;

import com.deliveryservice.api.common.error.ErrorCode;
import com.deliveryservice.api.common.error.TokenErrorCode;
import com.deliveryservice.api.common.exception.ApiException;
import com.deliveryservice.api.domain.token.business.TokenBuiness;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
@Component
public class AuthorizationInterceptor implements HandlerInterceptor {

    private final TokenBuiness tokenBuiness;



    //사전에 검증
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("Authorization Interceptor Uri : {}", request.getRequestURI());

        //web, chorme 의 경우 get,post Options == pass
        if (HttpMethod.OPTIONS.matches(request.getMethod()))return true;

        //js,html png resourec를  요청하는 경우 =pass
        if (handler instanceof ResourceHttpRequestHandler)return true;

        //TODO:header 검증
        var accessToken = request.getHeader("Authorization-token");
        if (accessToken == null){
            throw new ApiException(TokenErrorCode.Authorization_TOKEN_NOT_FOUND);
        }

        var userId = tokenBuiness.validationAccessToken(accessToken);

        if (userId != null){
            var requestContext = Objects.requireNonNull(RequestContextHolder.getRequestAttributes());
            requestContext.setAttribute("userId",userId, RequestAttributes.SCOPE_REQUEST);//RequestAttributes.SCOPE_REQUEST == 범위는 이번 요청동안만
            return true;
        }

        throw new ApiException(ErrorCode.BAD_REQUEST,"인증실패");
    }
}
