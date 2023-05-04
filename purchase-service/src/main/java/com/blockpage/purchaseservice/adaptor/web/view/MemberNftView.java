package com.blockpage.purchaseservice.adaptor.web.view;

import com.blockpage.purchaseservice.adaptor.infrastructure.MemberHasNftEntity;
import com.blockpage.purchaseservice.adaptor.infrastructure.NftType;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MemberNftView {

    private Long memberHasNftId;
    private String nftNames;
    private NftType nftType;
    private String nftImage;

    public static MemberNftView toViewFromEntity(MemberHasNftEntity memberHasNft) {
        return MemberNftView.builder()
            .memberHasNftId(memberHasNft.getMemberId())
            .nftNames(memberHasNft.getNftEntity().getNftName())
            .nftType(memberHasNft.getNftEntity().getNftType())
            .nftImage(memberHasNft.getNftEntity().getNftImage())
            .build();
    }
}

