package org.delivery.storeadmin.domain.authorization;

import com.deliveryservice.db.store.StoreRepository;
import com.deliveryservice.db.store.enums.StoreStatus;
import lombok.RequiredArgsConstructor;
import org.delivery.storeadmin.domain.authorization.model.UserSession;
import org.delivery.storeadmin.domain.user.service.StoreUserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthorizationService implements UserDetailsService {

    private final StoreUserService storeUserService;

    private final StoreRepository storeRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException { // 내가 커스텀한 로그인페이지에 username이 아니라 email이면 매개변수를 이걸로 변경해주면 된다

        var storeUserEntity = storeUserService.getRegisterUser(username);

        var storeEntity = storeRepository.findFirstByIdAndStatusOrderByIdDesc(
                storeUserEntity.get().getStoreId(),
                StoreStatus.REGISTERED
        );

        return storeUserEntity.map(it ->{

                    var userSession = UserSession.builder()
                            //user
                            .userId(it.getId())
                            .email(it.getEmail())
                            .password(it.getPassword())
                            .status(it.getStatus())
                            .role(it.getRole())
                            .registeredAt(it.getRegisteredAt())
                            .lastLoginAt(it.getLastLoginAt())
                            .unregisteredAt(it.getUnregisteredAt())

                            //store
                            .storeId(storeEntity.get().getId())
                            .storeName(storeEntity.get().getName())
                            .build();

                    return userSession;
                })
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }
}
