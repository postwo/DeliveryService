package com.deliveryservice.api.domain.userorder.controller;


import com.deliveryservice.api.common.annotation.UserSession;
import com.deliveryservice.api.common.api.Api;
import com.deliveryservice.api.domain.user.model.User;
import com.deliveryservice.api.domain.userorder.business.UserOrderBusiness;
import com.deliveryservice.api.domain.userorder.controller.model.UserOrderDetailResponse;
import com.deliveryservice.api.domain.userorder.controller.model.UserOrderRequest;
import com.deliveryservice.api.domain.userorder.controller.model.UserOrderResponse;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user-order")
@RequiredArgsConstructor
public class UserOrderApiController {

    private final UserOrderBusiness userOrderBusiness;


    //사용자 주문
    @PostMapping("")
    public Api<UserOrderResponse> userOrder(
            @Valid
            @RequestBody Api<UserOrderRequest> userOrderRequest,

            @Parameter(hidden = true) //swagger에서는 파라미터로 인식하기 때문에 이걸 걸어줘야 한다
            @UserSession
            User user //userApi 컨트롤러 참고
    ){
        var response = userOrderBusiness.userOrder(
                user,
                userOrderRequest.getBody()
        );
        return Api.OK(response);
    }


    //현재 진행중인 주문건
    @GetMapping("/current")
    public Api<List<UserOrderDetailResponse>> current(
            @Parameter(hidden = true)//swagger에서는 파라미터로 인식하기 때문에 이걸 걸어줘야 한다
            @UserSession
            User user
    ){
        var response = userOrderBusiness.current(user);
        return Api.OK(response);
    }



    //과거 주문 내역
    @GetMapping("/history")
    public Api<List<UserOrderDetailResponse>> history(
            @Parameter(hidden = true)//swagger에서는 파라미터로 인식하기 때문에 이걸 걸어줘야 한다
            @UserSession
            User user
    ){
        var response = userOrderBusiness.history(user);
        return Api.OK(response);
    }



    //주문 1건에 대한 내역
    @GetMapping("/id/{orderId}")
    public Api<UserOrderDetailResponse> read(
            @Parameter(hidden = true)//swagger에서는 파라미터로 인식하기 때문에 이걸 걸어줘야 한다
            @UserSession User user,

            @PathVariable(name = "orderId") Long orderId
    ){
        var response = userOrderBusiness.read(user, orderId);
        return Api.OK(response);
    }

}
