package com.blockpage.purchaseservice.adaptor.infrastructure.message.sync.block.requestbody;

import com.blockpage.purchaseservice.application.port.in.PurchaseUseCase.PurchaseQuery;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class BlockPayRequestParams {

    private Integer blockQuantity;
    private String type;

    public static BlockPayRequestParams addEssentialParams(PurchaseQuery query) {
        return BlockPayRequestParams.builder()
            .blockQuantity(query.getBlockQuantity())
            .type(query.getProductType())
            .build();
    }
}
