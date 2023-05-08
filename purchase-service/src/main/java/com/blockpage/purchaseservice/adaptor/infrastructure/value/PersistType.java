package com.blockpage.purchaseservice.adaptor.infrastructure.value;

import java.util.Arrays;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PersistType {
    PERMANENT(0, "영구소장"),
    RENTAL(1, "대여"),
    ;
    private int key;
    private String value;

    public static PersistType findPersistTypeByValue(String value) {
        return Arrays.stream(PersistType.values())
            .filter(t -> t.getValue().equals(value))
            .findFirst()
            .get();
    }
}
