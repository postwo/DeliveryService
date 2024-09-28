package com.deliveryservice.api.config.health;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("/open-api")
public class HealthOpenApiController {

    //test 용도
//    private final Producer producer;

    @GetMapping("/health")
    public void health(){
        log.info("health call");
//        producer.producer("delivery.exchange","delivery.key","hello");
    }
}
