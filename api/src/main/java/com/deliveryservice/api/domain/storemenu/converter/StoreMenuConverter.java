package com.deliveryservice.api.domain.storemenu.converter;

import com.deliveryservice.api.common.annotation.Converter;
import com.deliveryservice.api.common.error.ErrorCode;
import com.deliveryservice.api.common.exception.ApiException;
import com.deliveryservice.api.domain.storemenu.controller.model.StoreMenuRegisterRequest;
import com.deliveryservice.api.domain.storemenu.controller.model.StoreMenuResponse;
import com.deliveryservice.db.storemenu.StoreMenuEntity;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Converter
public class StoreMenuConverter {

    //프론트엔드에서 받아온 데이터를 서버에서 사용하는 엔티티(StoreMenuEntity) 객체로 변환
    public StoreMenuEntity toEntity(StoreMenuRegisterRequest request){

        return Optional.ofNullable(request)
                .map(it ->{

                    return StoreMenuEntity.builder()
                            .storeId(request.getStoreId())
                            .name(request.getName())
                            .amount(request.getAmount())
                            .thumbnailUrl(request.getThumbnailUrl())
                            .build()
                            ;

                })
                .orElseThrow(()-> new ApiException(ErrorCode.NULL_POINT));
    }


    //서버에서 사용 중인 엔티티 객체를 프론트엔드에 전달할 응답 객체
    public StoreMenuResponse toResponse(
            StoreMenuEntity storeMenuEntity
    ) {
        return Optional.ofNullable(storeMenuEntity)
                .map(it -> {
                    return StoreMenuResponse.builder()
                            .id(storeMenuEntity.getId())
                            .name(storeMenuEntity.getName())
                            .storeId(storeMenuEntity.getStoreId())
                            .amount(storeMenuEntity.getAmount())
                            .status(storeMenuEntity.getStatus())
                            .thumbnailUrl(storeMenuEntity.getThumbnailUrl())
                            .likeCount(storeMenuEntity.getLikeCount())
                            .sequence(storeMenuEntity.getSequence())
                            .build()
                            ;
                })
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT));
    }

    public List<StoreMenuResponse> toResponse(
            List<StoreMenuEntity> list
    ){
        return list.stream()
                .map(it -> toResponse(it))
                .collect(Collectors.toList());
    }


}