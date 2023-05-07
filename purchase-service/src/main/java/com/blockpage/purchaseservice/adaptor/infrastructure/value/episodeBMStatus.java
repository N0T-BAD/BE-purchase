package com.blockpage.purchaseservice.adaptor.infrastructure.value;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum episodeBMStatus {
    ON_SALE(0, "판매중"),
    STOP_SALE(1, "판매 중단"),
    ;
    private int key;
    private String value;
}
