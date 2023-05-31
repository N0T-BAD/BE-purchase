package com.blockpage.purchaseservice.adaptor.infrastructure.message.sync.member.requestbody;

import com.blockpage.purchaseservice.application.port.in.PurchaseUseCase.ChangePurchaseQuery;
import com.blockpage.purchaseservice.domain.Purchase;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class ChangeProfileSkinRequestBody {

    private String memberId;
    private String profileSkinImage;

    public static ChangeProfileSkinRequestBody addEssentialBody(Purchase purchase) {
        return ChangeProfileSkinRequestBody.builder()
            .profileSkinImage(purchase.getProfileSkinWrapper().getProfileSkinImage())
            .build();
    }
}
