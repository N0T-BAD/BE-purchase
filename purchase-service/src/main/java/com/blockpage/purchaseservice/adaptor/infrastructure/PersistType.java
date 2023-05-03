package com.blockpage.purchaseservice.adaptor.infrastructure;

import java.util.Arrays;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PersistType {
    PERMANENT(0, "영구 소장"),
    RENTAL(1, "대여"),
    ;
    private int key;
    private String value;

    public static PersistType findPersistTypeByKey(int num) {
        return Arrays.stream(PersistType.values())
            .filter(t -> t.getKey() == num)
            .findFirst()
            .get();
    }
}
