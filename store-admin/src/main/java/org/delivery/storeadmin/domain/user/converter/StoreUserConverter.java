package org.delivery.storeadmin.domain.user.converter;

import com.deliveryservice.db.store.StoreEntity;
import com.deliveryservice.db.storeuser.StoreUserEntity;
import lombok.RequiredArgsConstructor;
import org.delivery.storeadmin.domain.user.controller.model.StoreUserRegisterRequest;
import org.delivery.storeadmin.domain.user.controller.model.StoreUserResponse;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class StoreUserConverter { //변환만 해주는 목적

    public StoreUserEntity toEntity(
            StoreUserRegisterRequest request,
            StoreEntity storeEntity
    ){

        var storeName = request.getStoreName();

        return StoreUserEntity.builder()
                .email(request.getEmail())
                .password(request.getPassword())
                .role(request.getRole())
                .storeId(storeEntity.getId()) // TODO NULL 일때 에러 체크 확인 필요
                .build();
    }


    public StoreUserResponse toResponse(
            StoreUserEntity storeUserEntity,
            StoreEntity storeEntity
    ){
        return StoreUserResponse.builder()
                .user(
                        StoreUserResponse.UserResponse.builder()
                                .id(storeUserEntity.getId())
                                .email(storeUserEntity.getEmail())
                                .status(storeUserEntity.getStatus())
                                .role(storeUserEntity.getRole())
                                .registeredAt(storeUserEntity.getRegisteredAt())
                                .unregisteredAt(storeUserEntity.getUnregisteredAt())
                                .lastLoginAt(storeUserEntity.getLastLoginAt())
                                .build()
                )
                .store(
                        StoreUserResponse.StoreResponse.builder()
                                .id(storeEntity.getId())
                                .name(storeEntity.getName())
                                .build()
                )
                .build();
    }


}
