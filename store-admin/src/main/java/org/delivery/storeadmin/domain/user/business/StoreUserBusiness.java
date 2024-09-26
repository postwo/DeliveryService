package org.delivery.storeadmin.domain.user.business;

import com.deliveryservice.db.store.StoreRepository;
import com.deliveryservice.db.store.enums.StoreStatus;
import lombok.RequiredArgsConstructor;
import org.delivery.storeadmin.domain.user.controller.model.StoreUserRegisterRequest;
import org.delivery.storeadmin.domain.user.controller.model.StoreUserResponse;
import org.delivery.storeadmin.domain.user.converter.StoreUserConverter;
import org.delivery.storeadmin.domain.user.service.StoreUserService;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class StoreUserBusiness {

    private final StoreUserConverter storeUserConverter;
    private final StoreUserService storeUserService;

    private final StoreRepository storeRepository;  // TODO SERVICE 로 변경하기


    public StoreUserResponse register(
            StoreUserRegisterRequest request
    ){
        var storeEntity = storeRepository.findFirstByNameAndStatusOrderByIdDesc(request.getStoreName(), StoreStatus.REGISTERED);

        var entity = storeUserConverter.toEntity(request, storeEntity.get());

        var newEntity = storeUserService.register(entity);

        var response = storeUserConverter.toResponse(newEntity, storeEntity.get());

        return response;
    }
}
