package com.blockpage.purchaseservice.adaptor.infrastructure.mysql.value;

import java.util.Arrays;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ProductType {
    EPISODE_BM_PAID(0, "episodeBMPaid"),
    EPISODE_BM_FREE(1, "episodeBMFree"),
    PROFILE_SKIN(2, "profileSkin"),
    NFT(3, "nft"),
    ;
    private int key;
    private String value;

    public static ProductType findByValue(String value) {
        return Arrays.stream(ProductType.values())
            .filter(t -> t.getValue().equals(value))
            .findFirst()
            .get();
    }
}
