package com.deliveryservice.db.storemenu;

import com.deliveryservice.db.BaseEntity;
import com.deliveryservice.db.storemenu.enums.StoreMenuStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EqualsAndHashCode(callSuper = true)
@Table(name = "store_menu")
public class StoreMenuEntity extends BaseEntity {

    @Column(nullable = false)
    private Long storeId;

    @Column(length = 100, nullable = false)
    private String name;

    @Column(precision = 11, scale = 4, nullable = false)
    private BigDecimal amount;

    @Column(length = 50, nullable = false)
    @Enumerated(EnumType.STRING)
    private StoreMenuStatus status;

    @Column(length = 200, nullable = false)
    private String thumbnailUrl;

    private int likeCount; //int로 한이유는 default가 0이기 때문이다 만약에 null값이 들어가면 Integer로 하면된다

    private int sequence;

}
