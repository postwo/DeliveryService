package com.deliveryservice.api.domain.user.service;

import com.deliveryservice.api.common.error.ErrorCode;
import com.deliveryservice.api.common.error.UserErrorCode;
import com.deliveryservice.api.common.exception.ApiException;
import com.deliveryservice.db.user.UserEntity;
import com.deliveryservice.db.user.UserRepository;
import com.deliveryservice.db.user.enums.UserStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;


/*
* User 도메인 로직을 처리 하는 서비스
* */

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public UserEntity register(UserEntity userEntity){
        return Optional.ofNullable(userEntity)
                .map(it ->{
                    userEntity.setStatus(UserStatus.REGISTERED);
                    userEntity.setRegisteredAt(LocalDateTime.now());
                    return userRepository.save(userEntity);
                })
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT,"User NEtity Null"));
    }


    public UserEntity login(String email, String password){
        var entity = getUserWithThrow(email, password);
        return entity;
    }

    public UserEntity getUserWithThrow(String email, String password){
        return userRepository.findFirstByEmailAndPasswordAndStatusOrderByIdDesc(email,password,UserStatus.REGISTERED)
                .orElseThrow(()-> new ApiException(UserErrorCode.USER_NOT_FOUND));// 없을경우 예외가 터진다
    }

    public UserEntity getUserWithThrow(Long userId){
        return userRepository.findFirstByIdAndStatusOrderByIdDesc(userId,UserStatus.REGISTERED)
                .orElseThrow(()-> new ApiException(UserErrorCode.USER_NOT_FOUND));// 없을경우 예외가 터진다
    }
}
