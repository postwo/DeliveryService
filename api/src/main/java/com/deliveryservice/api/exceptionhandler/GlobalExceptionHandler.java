package com.deliveryservice.api.exceptionhandler;

import com.deliveryservice.api.common.api.Api;
import com.deliveryservice.api.common.error.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
@Order(value = Integer.MAX_VALUE) //값이 낮을수록 최우선적으로 실행된다  //max value이기 때문에 가장 마지막에 실행 적용
public class GlobalExceptionHandler {

    //스프링부트 자체 에서 나는 에러이든 뭐든 다 여기로 오게 된다
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Api<Object>> execption(Exception exception){
        log.error("",exception);// 어디서 몇번째라인에서 에러가 터졌는지 알수 있다

        return ResponseEntity.status(500).body(Api.ERROR(ErrorCode.SERVER_ERROR));
    }

}
