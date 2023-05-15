package com.blockpage.purchaseservice.adaptor.web.view;

import com.blockpage.purchaseservice.adaptor.infrastructure.mysql.value.NftType;
import com.blockpage.purchaseservice.adaptor.infrastructure.mysql.value.PersistType;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PurchaseView {

    private Long memberId;
    private PersistType persistType;
    private LocalDateTime expiredDate;

    private Long memberHasEpisodeBMId;
    private Long webtoonId;
    private Long episodeId;

    private Long memberHasNftId;
    private String nftNames;
    private NftType nftType;
    private String nftImage;

    private Long profileSkinId;
    private String profileSkinName;
    private String profileSkinImage;
    private boolean defaultSkin;

    public PurchaseView(Long memberHasNftId, String nftNames, NftType nftType, String nftImage) {
        this.memberHasNftId = memberHasNftId;
        this.nftNames = nftNames;
        this.nftType = nftType;
        this.nftImage = nftImage;
    }

    public PurchaseView(Long memberHasEpisodeBMId, Long episodeId, PersistType persistType, LocalDateTime expiredDate) {
        this.memberHasEpisodeBMId = memberHasEpisodeBMId;
        this.episodeId = episodeId;
        this.persistType = persistType;
        this.expiredDate = expiredDate;
    }

    public PurchaseView(Long profileSkinId, String profileSkinName, String profileSkinImage, boolean defaultSkin) {
        this.profileSkinId = profileSkinId;
        this.profileSkinName = profileSkinName;
        this.profileSkinImage = profileSkinImage;
        this.defaultSkin = defaultSkin;
    }
}
