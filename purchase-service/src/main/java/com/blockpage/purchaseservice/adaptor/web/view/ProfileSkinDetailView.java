package com.blockpage.purchaseservice.adaptor.web.view;

import com.blockpage.purchaseservice.adaptor.infrastructure.ProfileSkinEntity;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProfileSkinDetailView {

    private Long id;
    private String profileSkinName;
    private String profileSkinDescription;
    private String profileSkinBlockPrice;
    private String profileSkinImage;

    public static ProfileSkinDetailView toViewFromEntity(ProfileSkinEntity profileSkin) {
        return ProfileSkinDetailView.builder()
            .id(profileSkin.getId())
            .profileSkinName(profileSkin.getProfileSkinName())
            .profileSkinDescription(profileSkin.getProfileSkinDescription())
            .profileSkinBlockPrice(profileSkin.getProfileSkinBlockPrice())
            .profileSkinImage(profileSkin.getProfileSkinImage())
            .build();
    }
}
