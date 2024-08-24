package com.deliveryservice.api.domain.storemenu.controller.model;

import com.deliveryservice.db.storemenu.enums.StoreMenuStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StoreMenuResponse {

    private Long id; //등록된 메뉴의 아이디

    private Long storeId; //스토어 아이디

    private String name;

    private BigDecimal amount;

    private StoreMenuStatus status;

    private String thumbnailUrl;

    private int likeCount;

    private int sequence;
}