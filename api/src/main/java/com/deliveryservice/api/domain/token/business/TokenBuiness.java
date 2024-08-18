package com.deliveryservice.api.domain.token.business;


import com.deliveryservice.api.common.annotation.Business;
import com.deliveryservice.api.common.error.ErrorCode;
import com.deliveryservice.api.common.exception.ApiException;
import com.deliveryservice.api.domain.token.controller.model.TokenResponse;
import com.deliveryservice.api.domain.token.converter.TokenConverter;
import com.deliveryservice.api.domain.token.service.TokenService;
import com.deliveryservice.db.user.UserEntity;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
@Business
public class TokenBuiness {

        private final TokenService tokenService;
        private final TokenConverter tokenConverter;


        /**
         * 1. user entity user Id 추출
         * 2. access, refresh token 발행
         * 3. converter -> token response로 변경
         */

        public TokenResponse issueToken(UserEntity userEntity){
                return Optional.ofNullable(userEntity)
                        .map(ue->{

                        return ue.getId();
                        })
                        .map(userId->{
                         var accessToken = tokenService.issueAccessToken(userId);
                         var refreshToken = tokenService.issueRefreshToken(userId);
                         return tokenConverter.tokenResponse(accessToken,refreshToken);
                        })
                        .orElseThrow(()-> new ApiException(ErrorCode.NULL_POINT));

        }

        public Long validationAccessToken(String accessToken){
            var userId = tokenService.validationToken(accessToken);
            return userId;
        }
}
