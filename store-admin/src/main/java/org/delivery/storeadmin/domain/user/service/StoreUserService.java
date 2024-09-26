package org.delivery.storeadmin.domain.user.service;

import com.deliveryservice.db.storeuser.StoreUserEntity;
import com.deliveryservice.db.storeuser.StoreUserRepository;
import com.deliveryservice.db.storeuser.enums.StoreUserStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StoreUserService {

    private final StoreUserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    //가맹점 유저 회원가입
    public StoreUserEntity register(StoreUserEntity userEntity ){

        userEntity.setStatus(StoreUserStatus.REGISTERED); //회원 등록
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        userEntity.setRegisteredAt(LocalDateTime.now());

        return userRepository.save(userEntity);
    }

    //가맹점에 등록된 회원 검색
    public Optional<StoreUserEntity> getRegisterUser(String email) {
        return userRepository.findFirstByEmailAndStatusOrderByIdDesc(email,StoreUserStatus.REGISTERED);
    }



}
