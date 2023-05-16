package com.blockpage.purchaseservice.adaptor.infrastructure.mysql.value;

import java.util.Arrays;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ProductType {
    EPISODE_BM(0, "episode-bm"),
    PROFILE_SKIN(1, "profile-skin"),
    NFT(2, "nft"),
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
