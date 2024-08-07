package com.deliveryservice.api.config.swagger;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.core.jackson.ModelResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    //이렇게 하면 swagger에서도 snake 표기법( ' _ ' 이게 붙는다)을 사용할 수 있다
    //swagger 에서 schemas 에서 표기법을 확인할 수 있다
    @Bean
    public ModelResolver modelResolver(ObjectMapper objectMapper){ //여기 objectmapper는 내가 커스텀 한게 매개변수값으로 들어온다
        return new ModelResolver(objectMapper);
    }
}
