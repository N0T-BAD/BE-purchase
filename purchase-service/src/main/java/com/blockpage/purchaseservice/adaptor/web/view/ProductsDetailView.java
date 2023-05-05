package com.blockpage.purchaseservice.adaptor.web.view;

import com.blockpage.purchaseservice.adaptor.infrastructure.value.NftType;
import com.blockpage.purchaseservice.adaptor.infrastructure.value.WebtoonBMStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductsDetailView {

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
    private WebtoonBMStatus webtoonBMStatus;

    public ProductsDetailView(Long nftId, Long creatorId, String nftName, String nftDescription, Integer nftBlockPrice, String nftImage,
        NftType nftType) {
        this.nftId = nftId;
        this.creatorId = creatorId;
        this.nftName = nftName;
        this.nftDescription = nftDescription;
        this.nftBlockPrice = nftBlockPrice;
        this.nftImage = nftImage;
        this.nftType = nftType;
    }

    public ProductsDetailView(Long profileSkinId, String profileSkinName, String profileSkinDescription, Integer profileSkinBlockPrice,
        String profileSkinImage) {
        this.profileSkinId = profileSkinId;
        this.profileSkinName = profileSkinName;
        this.profileSkinDescription = profileSkinDescription;
        this.profileSkinBlockPrice = profileSkinBlockPrice;
        this.profileSkinImage = profileSkinImage;
    }

    public ProductsDetailView(Long webtoonBMId, Long webtoonId, Long episodeId, Integer webtoonBMBlockPrice,
        WebtoonBMStatus webtoonBMStatus) {
        WebtoonBMId = webtoonBMId;
        this.webtoonId = webtoonId;
        this.episodeId = episodeId;
        this.webtoonBMBlockPrice = webtoonBMBlockPrice;
        this.webtoonBMStatus = webtoonBMStatus;
    }
}
