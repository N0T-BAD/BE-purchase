package com.blockpage.purchaseservice.adaptor.infrastructure.mysql.value;

import java.util.Arrays;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ProductType {
    EPISODE_BM_PAID(0, "episode-bm-paid"),
    EPISODE_BM_FREE(1, "episode-bm-free"),
    PROFILE_SKIN(2, "profile-skin"),
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
