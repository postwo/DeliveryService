package org.delivery.storeadmin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
// 이것들을 달아준 이유
/*Not a managed type: class com.deliveryservice.db.storeuser.StoreUserEntity가
발생하는 이유는 Spring Data JPA가 StoreUserEntity 클래스를 관리하는 엔티티로 인식하지 못하기
때문입니다. 이러한 문제는 보통 JPA에서 엔티티 클래스를 스캔하지 못할 때 발생*/
@EnableJpaRepositories(basePackages = "com.deliveryservice.db")
@EntityScan(basePackages = "com.deliveryservice.db")
public class StoreAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(StoreAdminApplication.class, args);
    }
}
