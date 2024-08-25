package com.deliveryservice.db.userorder;

import com.deliveryservice.db.BaseEntity;
import com.deliveryservice.db.userorder.enums.UserOrderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Entity
@Table(name = "user_order")
public class UserOrderEntity extends BaseEntity {

    @Column(nullable = false)
    private Long userId;    // (user table) 1:n (UserOrderEntity)

    @Enumerated(EnumType.STRING)
    @Column(length = 50, nullable = false)
    private UserOrderStatus status;

    @Column(precision = 11, scale = 4, nullable = false)
    private BigDecimal amount; //총가격

    private LocalDateTime orderedAt; //주문일자

    @Column(name = "accpeted_at")
    private LocalDateTime acceptedAt; //주문수락일시

    private LocalDateTime cookingStartedAt; //조리 시작시간

    private LocalDateTime deliveryStartedAt; //배달시작시간

    private LocalDateTime receivedAt; //음식을 수령한시간
}
