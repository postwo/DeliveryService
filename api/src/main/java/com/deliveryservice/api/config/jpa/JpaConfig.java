package com.deliveryservice.api.config.jpa;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//동일한 패키지명이면 밑에방식을 사용안해도된다
//이거는 멀티 모듈일때 사용
//이렇게 설정 안하면 api 에서 만든 컨트롤러에서 실행 하면 다 에러가 뜬다 == 밑 방법 말고도 다른 방법이 있는데 패키지명을 com.deliveryservice.api 이걸로 똑같이 하면 해결 할 수 있다
@Configuration
@EntityScan(basePackages = "com.deliveryservice.db") //db 하위에 있는 entity를 다 scan한다는 것이다
@EnableJpaRepositories(basePackages = "com.deliveryservice.db") //db 폴더 하위에 있는 repository다 가져다 사용한다는 뜻이다
public class JpaConfig {


}
