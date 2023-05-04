package com.blockpage.purchaseservice.adaptor.web.view;

import com.blockpage.purchaseservice.adaptor.infrastructure.entity.MemberHasProfileSkinEntity;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MemberProfileSkinView {

    private Long profileSkinId;
    private String profileSkinName;
    private String profileSkinImage;
    private boolean defaultSkin;

    public static MemberProfileSkinView toViewFromEntity(MemberHasProfileSkinEntity memberHasProfileSkin) {
        return MemberProfileSkinView.builder()
            .profileSkinId(memberHasProfileSkin.getMemberId())
            .profileSkinName(memberHasProfileSkin.getProfileSkinEntity().getProfileSkinName())
            .profileSkinImage(memberHasProfileSkin.getProfileSkinEntity().getProfileSkinImage())
            .defaultSkin(memberHasProfileSkin.getDefaultSkin())
            .build();
    }
}
