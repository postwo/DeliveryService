package com.deliveryservice.api.common.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorCode implements ErrorCodeIfs{

    //ex )  BAD_REQUEST(HttpStatus.BAD_REQUEST.value(), 400(서버에러 코드), "잘못된 요청") == 항상HttpStatus.BAD_REQUEST.value(), 400(서버에러 코드)이거두개는 같지않을수도 있다
    //512에러 처럼

    OK(200, 200, "성공"),
    BAD_REQUEST(HttpStatus.BAD_REQUEST.value(), 400, "잘못된 요청"),
    SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value(), 500,"서버에러"),
    NULL_POINT(HttpStatus.INTERNAL_SERVER_ERROR.value(), 512,"Null Point")
    ;



    private final Integer httpStatusCode;
    private final Integer errorCode;
    private final String description;

    //getter 어노테이션 붙이면 이걸 작성할 필요없다
//    @Override
//    public Integer getHttpStatusCode() {
//        return this.httpStatusCode;
//    }
//
//    @Override
//    public Integer getErrorCode() {
//        return this.errorCode;
//    }
//
//    @Override
//    public String getDescription() {
//        return this.description;
//    }
}
