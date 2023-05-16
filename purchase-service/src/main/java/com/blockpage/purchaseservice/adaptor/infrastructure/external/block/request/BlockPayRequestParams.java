package com.blockpage.purchaseservice.adaptor.infrastructure.external.block.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class BlockPayRequestParams {

    private Integer quantity;

    public static BlockPayRequestParams addEssentialParams(Integer quantity) {
        return BlockPayRequestParams.builder()
            .quantity(quantity)
            .build();
    }
}
