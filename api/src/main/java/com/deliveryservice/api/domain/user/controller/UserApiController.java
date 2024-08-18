package com.deliveryservice.api.domain.user.controller;


import com.deliveryservice.api.common.annotation.UserSession;
import com.deliveryservice.api.common.api.Api;
import com.deliveryservice.api.domain.user.businiess.UserBusiness;
import com.deliveryservice.api.domain.user.controller.model.UserResponse;
import com.deliveryservice.api.domain.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/user")
public class UserApiController {

    private final UserBusiness userBusiness;

    @GetMapping("/me")
    public Api<UserResponse> me(@UserSession User user){

        var response = userBusiness.me(user);
        return Api.OK(response);    
    }

}
