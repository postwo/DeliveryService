package com.deliveryservice.api.domain.token.converter;

import com.deliveryservice.api.common.annotation.Converter;
import com.deliveryservice.api.common.error.ErrorCode;
import com.deliveryservice.api.common.exception.ApiException;
import com.deliveryservice.api.domain.token.controller.model.TokenResponse;
import com.deliveryservice.api.domain.token.model.TokenDto;
import lombok.RequiredArgsConstructor;

import java.util.Objects;

@RequiredArgsConstructor
@Converter
public class TokenConverter {

    public TokenResponse tokenResponse(TokenDto accessToken, TokenDto refreshToken){

        //토큰 값이 null이면 발생
        Objects.requireNonNull(accessToken,() -> {throw new ApiException(ErrorCode.NULL_POINT);});

        Objects.requireNonNull(refreshToken,() -> {throw new ApiException(ErrorCode.NULL_POINT);});

        return TokenResponse.builder()
                .accessToken(accessToken.getToken())
                .accessTokenExpiredAt(accessToken.getExpiredAt())
                .refreshToken(refreshToken.getToken())
                .refreshTokenExpiredAt(refreshToken.getExpiredAt())
                .build();
    }
}
