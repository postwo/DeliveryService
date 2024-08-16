package com.deliveryservice.api.account;

import com.deliveryservice.api.account.model.AccountMeResponse;
import com.deliveryservice.api.common.api.Api;
import com.deliveryservice.api.common.error.ErrorCode;
import com.deliveryservice.api.common.exception.ApiException;
import com.deliveryservice.db.account.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/account")
@RequiredArgsConstructor
public class AccountApiController {

    private final AccountRepository accountRepository;

//    @GetMapping("/")
//    public void save (){
//        var account = AccountEntity.builder().build();
//        accountRepository.save(account); //그냥 이렇게 하면 accountRepository를 모른다 그러므로 api build gradle에다가 jpa의존성을 주입
//    }


//    @GetMapping("/me")
//    public Api<Object> me (){
//
//        var response = AccountMeResponse.builder()
//                .name("홍길동")
//                .email("codo7717@naver.com")
//                .registeredAt(LocalDateTime.now())
//                .build();
//
//        return Api.ERROR(UserErrorCode.USER_NOT_FOUND,"홍길동 이라는 사용자 없음");
//    }

    //컨트롤러,서비스에서는 항상 성공처리만 바라보게 하고 여기서 에러가 발생하는거는 ExcetpionHandler에서 처리해준다
    @GetMapping("/me")
    public Api<AccountMeResponse> me (){

        var response = AccountMeResponse.builder()
                .name("홍길동")
                .email("codo7717@naver.com")
                .registeredAt(LocalDateTime.now())
                .build();

        var str = "안녕하세요";
        var age =0;
        try{
            Integer.parseInt(str);
        }catch (Exception e){
            throw new ApiException(ErrorCode.SERVER_ERROR,e, "사용자 me 호출시 에러 발생");
        }
        Integer.parseInt(str);

        return Api.OK(response);
    }


}
