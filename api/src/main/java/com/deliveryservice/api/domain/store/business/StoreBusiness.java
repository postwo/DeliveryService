package com.deliveryservice.api.domain.store.business;

import com.deliveryservice.api.common.annotation.Business;
import com.deliveryservice.api.domain.store.controller.model.StoreRegisterRequest;
import com.deliveryservice.api.domain.store.controller.model.StoreResponse;
import com.deliveryservice.api.domain.store.converter.StoreConverter;
import com.deliveryservice.api.domain.store.service.StoreService;
import com.deliveryservice.db.store.enums.StoreCategory;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Business
@RequiredArgsConstructor
public class StoreBusiness {

    private final StoreService storeService;
    private final StoreConverter storeConverter;

    //클라이언트로부터 받은 가게 등록 요청을 처리하고, 최종적으로 클라이언트에게 응답을 반환
    public StoreResponse register(
            StoreRegisterRequest storeRegisterRequest
    ){
        // req -> entity -> response
        var entity = storeConverter.toEntity(storeRegisterRequest);
        var newEntity = storeService.register(entity);
        var response = storeConverter.toResponse(newEntity);
        return response;
    }

    public List<StoreResponse> searchCategory(
            StoreCategory storeCategory
    ){
        // entity list -> response list

        var storeList = storeService.searchByCategory(storeCategory);

        return storeList.stream()
                .map(storeConverter::toResponse)
                .collect(Collectors.toList());
    }
}
