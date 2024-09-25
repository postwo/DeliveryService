package org.delivery.storeadmin.config.jpa;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan(basePackages = "org.delivery.db") //지정한 패키지에서 JPA 엔티티 클래스를 스캔하여 관리
@EnableJpaRepositories(basePackages = "org.delivery.db") //지정한 패키지에서 JPA 레포지토리 인터페이스를 스캔하여 활성화.
public class JpaConfig {



}
