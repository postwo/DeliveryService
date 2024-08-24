package com.deliveryservice.api.domain.storemenu.controller;

import com.deliveryservice.api.common.api.Api;
import com.deliveryservice.api.domain.storemenu.buiness.StoreMenuBusiness;
import com.deliveryservice.api.domain.storemenu.controller.model.StoreMenuResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/store-menu")
@RequiredArgsConstructor
public class StoreMenuApiController { //로그인된 상태

    private final StoreMenuBusiness storeMenuBusiness;

    @GetMapping("/search")
    public Api<List<StoreMenuResponse>> search(
            @RequestParam(name = "storeId") Long storeId
    ){

        var response = storeMenuBusiness.search(storeId);
        return Api.OK(response);
    }
}
