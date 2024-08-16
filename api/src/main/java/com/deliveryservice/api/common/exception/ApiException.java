package com.deliveryservice.api.common.exception;

import com.deliveryservice.api.common.error.ErrorCodeIfs;
import lombok.Getter;

@Getter
public class ApiException extends RuntimeException implements ApiExceptionIfs{

    private final ErrorCodeIfs errorCodeIfs;
    private final String errorDescription;//상세한 에러메시지 //내가 정의한 메시지를 지정

    public ApiException(ErrorCodeIfs errorCodeIfs){
        super(errorCodeIfs.getDescription());
        this.errorCodeIfs = errorCodeIfs;
        this.errorDescription = errorCodeIfs.getDescription();
    }

    public ApiException(ErrorCodeIfs errorCodeIfs, String errorDescription){
        super(errorCodeIfs.getDescription());
        this.errorCodeIfs = errorCodeIfs;
        this.errorDescription = errorDescription;
    }


    public ApiException(ErrorCodeIfs errorCodeIfs,Throwable tx){
        super(tx);
        this.errorCodeIfs = errorCodeIfs;
        this.errorDescription = errorCodeIfs.getDescription();
    }


    public ApiException(ErrorCodeIfs errorCodeIfs,Throwable tx,String errorDescription){
        super(tx);
        this.errorCodeIfs = errorCodeIfs;
        this.errorDescription = errorDescription; //내가 원하는 메시지
    }


}
