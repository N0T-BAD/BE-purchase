package com.blockpage.purchaseservice.adaptor.web.view;

import com.blockpage.purchaseservice.adaptor.infrastructure.mysql.value.NftType;
import com.blockpage.purchaseservice.adaptor.infrastructure.mysql.value.EpisodeBMStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductsView {

    private Long nftId;
    private Long creatorId;
    private String nftName;
    private String nftDescription;
    private Integer nftBlockPrice;
    private String nftImage;
    private NftType nftType;

    private Long profileSkinId;
    private String profileSkinName;
    private String profileSkinDescription;
    private Integer profileSkinBlockPrice;
    private String profileSkinImage;

    private Long WebtoonBMId;
    private Long webtoonId;
    private Long episodeId;
    private Integer webtoonBMBlockPrice;
    private EpisodeBMStatus episodeBMStatus;

    public ProductsView(Long nftId, Long creatorId, String nftName, String nftDescription, Integer nftBlockPrice, String nftImage,
        NftType nftType) {
        this.nftId = nftId;
        this.creatorId = creatorId;
        this.nftName = nftName;
        this.nftDescription = nftDescription;
        this.nftBlockPrice = nftBlockPrice;
        this.nftImage = nftImage;
        this.nftType = nftType;
    }

    public ProductsView(Long profileSkinId, String profileSkinName, String profileSkinDescription, Integer profileSkinBlockPrice,
        String profileSkinImage) {
        this.profileSkinId = profileSkinId;
        this.profileSkinName = profileSkinName;
        this.profileSkinDescription = profileSkinDescription;
        this.profileSkinBlockPrice = profileSkinBlockPrice;
        this.profileSkinImage = profileSkinImage;
    }

    public ProductsView(Long webtoonBMId, Long webtoonId, Long episodeId, Integer webtoonBMBlockPrice,
        EpisodeBMStatus episodeBMStatus) {
        WebtoonBMId = webtoonBMId;
        this.webtoonId = webtoonId;
        this.episodeId = episodeId;
        this.webtoonBMBlockPrice = webtoonBMBlockPrice;
        this.episodeBMStatus = episodeBMStatus;
    }
}
