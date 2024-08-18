package com.deliveryservice.api.domain.store.controller;

import com.deliveryservice.api.common.api.Api;
import com.deliveryservice.api.domain.store.business.StoreBusiness;
import com.deliveryservice.api.domain.store.controller.model.StoreResponse;
import com.deliveryservice.db.store.enums.StoreCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/store")
public class StoreApiController {


    private final StoreBusiness storeBusiness;

    @GetMapping("/search")
    public Api<List<StoreResponse>> search(
            @RequestParam(required = false)
            StoreCategory storeCategory
    ){
        var response = storeBusiness.searchCategory(storeCategory);
        return Api.OK(response);
    }
}