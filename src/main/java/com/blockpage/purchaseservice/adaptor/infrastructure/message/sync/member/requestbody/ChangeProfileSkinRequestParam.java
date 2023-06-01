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
public class ChangeProfileSkinRequestParam {

    private String profileSkin;

    public static ChangeProfileSkinRequestParam addEssentialBody(Purchase purchase) {
        return ChangeProfileSkinRequestParam.builder()
            .profileSkin(purchase.getProfileSkinWrapper().getProfileSkinImage())
            .build();
    }
}
