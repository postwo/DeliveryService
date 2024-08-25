package com.deliveryservice.api.domain.userordermenu.converter;

import com.deliveryservice.api.common.annotation.Converter;
import com.deliveryservice.db.storemenu.StoreMenuEntity;
import com.deliveryservice.db.userorder.UserOrderEntity;
import com.deliveryservice.db.userordermenu.UserOrderMenuEntity;

@Converter
public class UserOrderMenuConverter {

    public UserOrderMenuEntity toEntity(
            UserOrderEntity userOrderEntity,
            StoreMenuEntity storeMenuEntity
    ){
        return UserOrderMenuEntity.builder()
                .userOrderId(userOrderEntity.getId())
                .storeMenuId(storeMenuEntity.getId())
                .build()
                ;
    }

}