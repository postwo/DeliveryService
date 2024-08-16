package com.deliveryservice.api.common.exception;

import com.deliveryservice.api.common.error.ErrorCodeIfs;

public interface ApiExceptionIfs {
    ErrorCodeIfs getErrorCodeIfs();
    String getErrorDescription();
}
