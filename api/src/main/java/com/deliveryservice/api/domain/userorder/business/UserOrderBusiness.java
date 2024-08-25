package com.deliveryservice.api.domain.userorder.business;

import com.deliveryservice.api.common.annotation.Business;
import com.deliveryservice.api.domain.storemenu.service.StoreMenuService;
import com.deliveryservice.api.domain.user.model.User;
import com.deliveryservice.api.domain.userorder.controller.model.UserOrderRequest;
import com.deliveryservice.api.domain.userorder.controller.model.UserOrderResponse;
import com.deliveryservice.api.domain.userorder.converter.UserOrderConverter;
import com.deliveryservice.api.domain.userorder.service.UserOrderService;
import com.deliveryservice.api.domain.userordermenu.converter.UserOrderMenuConverter;
import com.deliveryservice.api.domain.userordermenu.service.UserOrderMenuService;
import lombok.RequiredArgsConstructor;

import java.util.stream.Collectors;

@Business
@RequiredArgsConstructor
public class UserOrderBusiness {

    private final UserOrderService userOrderService;

    private final UserOrderConverter userOrderConverter;

    private final StoreMenuService storeMenuService;

    private final UserOrderMenuConverter userOrderMenuConverter;
    private final UserOrderMenuService userOrderMenuService;

    //1. 사용자 ,메뉴 id
    //2. userOrder 생성
    //3. userOrderMenu 생성
    //4. 응답 생성
    public UserOrderResponse userOrder(User user, UserOrderRequest body) {
        var storeMenuEntityList = body.getStoreMenuIdList().stream()
                .map(it-> storeMenuService.getStoreMenuWithThrow(it))
                .collect(Collectors.toList());

        var userOrderEntity = userOrderConverter.toEntity(user,storeMenuEntityList);

        //주문
        var newUserOrderEntity = userOrderService.order(userOrderEntity);

        // 맵핑
        var userOrderMenuEntityList = storeMenuEntityList.stream()
                .map(it ->{
                    // menu +user order
                    var userOrderMenuEntity = userOrderMenuConverter.toEntity(newUserOrderEntity, it);
                    return userOrderMenuEntity;
                })
                .collect(Collectors.toList());


        // 주문내역 기록 남기기
        userOrderMenuEntityList.forEach(it ->{
            userOrderMenuService.order(it);
        });


        // response 로 변경
        return userOrderConverter.toResponse(newUserOrderEntity);
    }
}
