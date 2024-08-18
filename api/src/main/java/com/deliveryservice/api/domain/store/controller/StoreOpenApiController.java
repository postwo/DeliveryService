package com.deliveryservice.api.domain.store.controller;

import com.deliveryservice.api.common.api.Api;
import com.deliveryservice.api.domain.store.business.StoreBusiness;
import com.deliveryservice.api.domain.store.controller.model.StoreRegisterRequest;
import com.deliveryservice.api.domain.store.controller.model.StoreResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/open-api/store")
@RequiredArgsConstructor
public class StoreOpenApiController { //로그인한 사용자가 사용하는게 아니라 직원들이 사용

    private final StoreBusiness storeBusiness;


    @PostMapping("/register")
    public Api<StoreResponse> register(
            @Valid
            @RequestBody Api<StoreRegisterRequest> request
    ){
        var response = storeBusiness.register(request.getBody());
        return Api.OK(response);
    }

}
