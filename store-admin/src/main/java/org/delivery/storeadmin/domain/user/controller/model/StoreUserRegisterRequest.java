package org.delivery.storeadmin.domain.user.controller.model;

import com.deliveryservice.db.storeuser.enums.StoreUserRole;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class StoreUserRegisterRequest { //앞단에서 받아오는 정보

    @NotBlank
    private String storeName;

    @NotBlank
    private String email;

    @NotBlank
    private String password;

    @NotBlank
    private StoreUserRole role;
}