package com.deliveryservice.api.domain.user.businiess;

import com.deliveryservice.api.common.annotation.Business;
import com.deliveryservice.api.common.error.ErrorCode;
import com.deliveryservice.api.common.exception.ApiException;
import com.deliveryservice.api.domain.token.business.TokenBuiness;
import com.deliveryservice.api.domain.token.controller.model.TokenResponse;
import com.deliveryservice.api.domain.user.controller.model.UserLoginRequest;
import com.deliveryservice.api.domain.user.controller.model.UserRegisterRequest;
import com.deliveryservice.api.domain.user.controller.model.UserResponse;
import com.deliveryservice.api.domain.user.converter.UserConverter;
import com.deliveryservice.api.domain.user.model.User;
import com.deliveryservice.api.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
@Business
public class UserBusiness {

    private final UserService userService;
    private final UserConverter userConverter;
    private final TokenBuiness tokenBuiness;

    /*사용자에 대한 가입처리 로직
    * 1. request -> entity
    * 2. entity -> save
    * 3. save entity -> response
    * 4. response return
    * */
    public UserResponse register(UserRegisterRequest request) {

//        var entity = userConverter.toEntity(request);
//        var newEntity = userService.register(entity);
//        var response = userConverter.toResponse(newEntity);
//        return response;

        return Optional.ofNullable(request)
                .map(userConverter::toEntity)
                .map(userService::register)
                .map(userConverter::toResponse)
                .orElseThrow(()-> new ApiException(ErrorCode.NULL_POINT,"request null"));

    }


    /*
    * 1. email, password 를 가지고 사용자 체크
    * 2. user entity 로그인 확인
    * 3. token 생성
    * 4. token response
    * */
    public TokenResponse login(UserLoginRequest request) {
        var userEntity = userService.login(request.getEmail(), request.getPassword());

        //TODO 토큰 생성 로직으로 변경하기
        var tokenResponse = tokenBuiness.issueToken(userEntity);

        return tokenResponse;
    }


//    public UserResponse me(Long userId) {
//        var userEntity = userService.getUserWithThrow(userId);
//        var response = userConverter.toResponse(userEntity);
//        return response;
//    }

    public UserResponse me(User user) {
        var userEntity = userService.getUserWithThrow(user.getId());
        var response = userConverter.toResponse(userEntity);
        return response;
    }
}
