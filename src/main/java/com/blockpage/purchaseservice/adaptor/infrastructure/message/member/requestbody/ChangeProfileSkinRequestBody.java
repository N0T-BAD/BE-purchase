package com.blockpage.purchaseservice.adaptor.infrastructure.message.member.requestbody;

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

    public static ChangeProfileSkinRequestBody addEssentialBody(ChangePurchaseQuery query, Purchase purchase) {
        return ChangeProfileSkinRequestBody.builder()
            .memberId(query.getMemberId())
            .profileSkinImage(purchase.getProfileSkinWrapper().getProfileSkinImage())
            .build();
    }
}
