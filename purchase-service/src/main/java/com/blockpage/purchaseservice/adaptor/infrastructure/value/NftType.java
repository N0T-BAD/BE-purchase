package com.blockpage.purchaseservice.adaptor.infrastructure.value;

import java.util.Arrays;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum NftType {

    ONE_BLOCK_DISCOUNT_BY_AUTHOR(0, "작가별 블럭 1개 할인권"),
    TWO_BLOCK_DISCOUNT_BY_AUTHOR(1, "작가별 블럭 2개 할인권"),
    ;

    private int key;
    private String value;

    public static NftType findNftTypeByKey(int num) {
        return Arrays.stream(NftType.values())
            .filter(t -> t.getKey() == num)
            .findFirst()
            .get();
    }
}
