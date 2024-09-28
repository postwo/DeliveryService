package org.delivery.storeadmin.domain.userorder.consumer;

import com.deliveryservice.common.message.model.UserOrderMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class UserOrderConsumer {

//    private final UserOrderBusiness userOrderBusiness;

    @RabbitListener(queues = "delivery.queue") //어떤 큐로 부터 받아올꺼냐는 뜻이다
    public void userOrderConsumer(
            UserOrderMessage userOrderMessage
    ){
        log.info("message queue >> {}", userOrderMessage);
//        userOrderBusiness.pushUserOrder(userOrderMessage);
    }
}
