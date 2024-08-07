package com.deliveryservice.api.account;

import com.deliveryservice.api.account.model.AccountMeResponse;
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


    @GetMapping("/me")
    public AccountMeResponse me (){
        return AccountMeResponse.builder()
                .name("홍길동")
                .email("codo7717@naver.com")
                .registeredAt(LocalDateTime.now())
                .build();
    }

}
