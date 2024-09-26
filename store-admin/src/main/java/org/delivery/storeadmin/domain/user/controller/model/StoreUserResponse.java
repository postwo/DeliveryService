package org.delivery.storeadmin.domain.user.controller.model;

import com.deliveryservice.db.storeuser.enums.StoreUserRole;
import com.deliveryservice.db.storeuser.enums.StoreUserStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StoreUserResponse { //클래스 안에 정의된 클래스를 **"내부 클래스(Inner Class)"**라고 부른다

    /*정확히 말하면, 위 코드에서 사용한 static 키워드를 붙인 내부 클래스는 **정적 내부 클래스(Static Nested Class)**에 해당합니다. 내부 클래스는 크게 두 가지 유형으로 나눌 수 있습니다:
Inner Class (비정적 내부 클래스): static 키워드를 사용하지 않은 내부 클래스입니다. 바깥 클래스의 인스턴스와 밀접한 관계가 있으며, 바깥 클래스의 인스턴스 변수와 메서드에 직접 접근할 수 있습니다.
Static Nested Class (정적 내부 클래스): static 키워드를 사용한 내부 클래스입니다. 바깥 클래스의 인스턴스와 독립적이며, 바깥 클래스의 인스턴스 변수에 접근할 수 없습니다. 외부에서 인스턴스 생성 시 바깥 클래스의 인스턴스 없이도 사용할 수 있습니다.*/

    private UserResponse user;
    private StoreResponse store;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class UserResponse{ //static을 사용한 이유는 바깥쪽에서 접근만 못하게 막기 위해 사용   //사용자 정보
        private Long id;
        private String email;

        private StoreUserStatus status;

        private StoreUserRole role;

        private LocalDateTime registeredAt;

        private LocalDateTime unregisteredAt;

        private LocalDateTime lastLoginAt;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class StoreResponse{ //가게 정보
        private Long id;
        private String name;
    }
}
