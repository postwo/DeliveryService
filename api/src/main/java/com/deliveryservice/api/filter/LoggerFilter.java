package com.deliveryservice.api.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;

@Component
@Slf4j
public class LoggerFilter implements Filter {
    /*  이 필터는 클라이언트가 보낸 요청과 서버가 응답한 내용을 로그로 기록하는 기능을 수행합니다. 구체적으로, 이 필터는 다음과 같은 작업을 합니다:

        클라이언트 요청에 대한 정보 로그:
        헤더 정보: 클라이언트가 요청을 보낼 때 포함한 헤더 정보를 로그로 기록합니다.
        본문 정보: 클라이언트가 요청 본문에 포함한 내용을 로그로 기록합니다.
        URI와 메소드: 요청이 들어온 URI와 HTTP 메소드(GET, POST 등)를 로그로 기록합니다.
    
        서버 응답에 대한 정보 로그:
        헤더 정보: 서버가 클라이언트에게 응답할 때 설정한 응답 헤더 정보를 로그로 기록합니다.
        본문 정보: 서버가 클라이언트에게 응답 본문으로 보내는 내용을 로그로 기록합니다.
        응답 내용의 복사: res.copyBodyToResponse()를 호출하여 응답 본문을 클라이언트에게 실제로 전달합니다.
        * */

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        var req = new ContentCachingRequestWrapper((HttpServletRequest) servletRequest);
        var res = new ContentCachingResponseWrapper((HttpServletResponse) servletResponse);

        filterChain.doFilter(req,res);

        //request 정보
        var headerNames = req.getHeaderNames();
        var headerValues = new StringBuilder();

        headerNames.asIterator().forEachRemaining(headerKey ->{
            var headerValue = req.getHeader(headerKey); //클라이언트가 서버에 보낸 요청의 세부 정보

            //authorization-token :??? , user-agent :???
            headerValues.append("[").append(headerKey).append(" : ").append(headerValue).append("] ");
        });

        var requestBody = new String(req.getContentAsByteArray());
        var uri = req.getRequestURI();
        var method = req.getMethod();

        log.info(">>>> uri: {}, method: {} ,header : {}, body : {}",uri,method,headerValues.toString(),requestBody);


        //response 정보
        var responseHeaderValues = new StringBuilder();

        res.getHeaderNames().forEach(headerKey ->{
            var headerValue = res.getHeader(headerKey); //서버가 클라이언트에게 응답

            responseHeaderValues.append("[").append(headerValue).append(" : ").append(headerValue).append("] ");
        });


        var responseBody = new String(res.getContentAsByteArray());

        log.info("<<<<< uri: {}, method: {} , header :{} , body : {}",uri,method,responseHeaderValues.toString(),responseBody);

        res.copyBodyToResponse();
    }
}
