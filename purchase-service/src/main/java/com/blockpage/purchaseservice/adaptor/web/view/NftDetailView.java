package com.blockpage.purchaseservice.adaptor.web.view;

import com.blockpage.purchaseservice.adaptor.infrastructure.NftEntity;
import com.blockpage.purchaseservice.adaptor.infrastructure.NftType;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class NftDetailView {

    private Long id;
    private Long creatorId;
    private String nftName;
    private String nftDescription;
    private Integer nftBlockPrice;
    private String nftImage;
    private NftType nftType;

    public static NftDetailView toViewFromEntity(NftEntity nft) {
        return NftDetailView.builder()
            .id(nft.getId())
            .creatorId(nft.getCreatorId())
            .nftName(nft.getNftName())
            .nftDescription(nft.getNftDescription())
            .nftBlockPrice(nft.getNftBlockPrice())
            .nftImage(nft.getNftImage())
            .nftType(nft.getNftType())
            .build();
    }
}
