package com.deliveryservice.api.domain.store.converter;

import com.deliveryservice.api.common.annotation.Converter;
import com.deliveryservice.api.common.error.ErrorCode;
import com.deliveryservice.api.common.exception.ApiException;
import com.deliveryservice.api.domain.store.controller.model.StoreRegisterRequest;
import com.deliveryservice.api.domain.store.controller.model.StoreResponse;
import com.deliveryservice.db.store.StoreEntity;

import java.util.Optional;

@Converter
public class StoreConverter {

    //클라이언트로부터 받은 요청 데이터 (StoreRegisterRequest)를 서버의 엔티티 객체 (StoreEntity)로 변환
    public StoreEntity toEntity(
            StoreRegisterRequest request
    ) {
        return Optional.ofNullable(request)
                .map(it -> {
                    return StoreEntity.builder()
                            .name(request.getName())
                            .address(request.getAddress())
                            .category(request.getStoreCategory())
                            .minimumAmount(request.getMinimumAmount())
                            .minimumDeliveryAmount(request.getMinimumDeliveryAmount())
                            .thumbnailUrl(request.getThumbnailUrl())
                            .phoneNumber(request.getPhoneNumber())
                            .build()
                            ;
                })
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT));
    }

    //서버의 엔티티 객체 (StoreEntity)를 클라이언트에 반환할 응답 DTO (StoreResponse)로 변환
    public StoreResponse toResponse(
            StoreEntity entity
    ) {
        return Optional.ofNullable(entity)
                .map(it -> {
                    return StoreResponse.builder()
                            .id(entity.getId())
                            .name(entity.getName())
                            .status(entity.getStatus())
                            .category(entity.getCategory())
                            .address(entity.getAddress())
                            .minimumAmount(entity.getMinimumAmount())
                            .minimumDeliveryAmount(entity.getMinimumDeliveryAmount())
                            .thumbnailUrl(entity.getThumbnailUrl())
                            .phoneNumber(entity.getPhoneNumber())
                            .star(entity.getStar())
                            .build();
                })
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT));

    }
}
