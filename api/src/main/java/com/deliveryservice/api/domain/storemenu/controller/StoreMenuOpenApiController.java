package com.deliveryservice.api.domain.storemenu.controller;

import com.deliveryservice.api.common.api.Api;
import com.deliveryservice.api.domain.storemenu.buiness.StoreMenuBusiness;
import com.deliveryservice.api.domain.storemenu.controller.model.StoreMenuRegisterRequest;
import com.deliveryservice.api.domain.storemenu.controller.model.StoreMenuResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/open-api/store-menu")
@RequiredArgsConstructor
public class StoreMenuOpenApiController { //로그인 되지 않은 상태

    private final StoreMenuBusiness storeMenuBusiness;

    @PostMapping("/register")
    public Api<StoreMenuResponse> register(
            @Valid
            @RequestBody Api<StoreMenuRegisterRequest> request
    ){
        var req = request.getBody();
        var response = storeMenuBusiness.register(req);
        return Api.OK(response);
    }

}
