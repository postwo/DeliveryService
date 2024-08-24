package com.deliveryservice.api.domain.storemenu.buiness;

import com.deliveryservice.api.common.annotation.Business;
import com.deliveryservice.api.domain.storemenu.controller.model.StoreMenuRegisterRequest;
import com.deliveryservice.api.domain.storemenu.controller.model.StoreMenuResponse;
import com.deliveryservice.api.domain.storemenu.converter.StoreMenuConverter;
import com.deliveryservice.api.domain.storemenu.service.StoreMenuService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Business
@Slf4j
public class StoreMenuBusiness {

    private final StoreMenuService storeMenuService;
    private final StoreMenuConverter storeMenuConverter;


    public StoreMenuResponse register(
            StoreMenuRegisterRequest request
    ){
        // req -> entity -> save -> response
        var entity = storeMenuConverter.toEntity(request);
        var newEntity = storeMenuService.register(entity);
        var response = storeMenuConverter.toResponse(newEntity);
        return response;
    }

    public List<StoreMenuResponse> search(
            Long storeId
    ){
        var list = storeMenuService.getStoreMenuByStoreId(storeId);

        return list.stream()
                .map(it ->{
                    return storeMenuConverter.toResponse(it);
                })
                //.map(storeMenuConverter::toResponse)
                .collect(Collectors.toList());
    }
}
