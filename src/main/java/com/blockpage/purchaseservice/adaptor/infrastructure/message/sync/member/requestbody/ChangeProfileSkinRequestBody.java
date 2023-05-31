package com.blockpage.purchaseservice.adaptor.infrastructure.message.sync.member.requestbody;

import com.blockpage.purchaseservice.domain.Purchase;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class ChangeProfileSkinRequestBody {

    private String profileSkinImage;

    public static ChangeProfileSkinRequestBody addEssentialBody(Purchase purchase) {
        return ChangeProfileSkinRequestBody.builder()
            .profileSkinImage(purchase.getProfileSkinWrapper().getProfileSkinImage())
            .build();
    }
}
