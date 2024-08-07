package com.deliveryservice.api.account.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
// 이거를 각 클래스 마다 설정해주기는 힘들기 때문에 ObjectMapperconfig에서 커스텀해서 전체 설정을 해준다
//@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class) //ISO 8601(-,:으로 분리) 에서 시간표기법을 snake로 변경 하면 우리가 보기 편한 시간대로 보여준다
public class AccountMeResponse {

    private String email;

    private String name;

    private LocalDateTime registeredAt;


}
