package com.deliveryservice.db.userordermenu;

import com.deliveryservice.db.BaseEntity;
import com.deliveryservice.db.userordermenu.enums.UserOrderMenuStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Entity
@Table(name = "user_order_menu")
public class UserOrderMenuEntity extends BaseEntity { //중간 연결 엔티티

    @Column(nullable = false)
    private Long userOrderId;   // (userorder)1 : n(UserOrderMenuEntity) == n의 입장에서 가지고 오는거다
    @Column(nullable = false)
    private Long storeMenuId;   // (storemenu)1 : n(UserOrderMenuEntity) == n의 입장에서 가지고 오는거다

    @Enumerated(EnumType.STRING)
    @Column(length = 50, nullable = false ,name = "statusl")
    private UserOrderMenuStatus status; //누락이나 취소가 될수도 있기 때문에 추가
}
