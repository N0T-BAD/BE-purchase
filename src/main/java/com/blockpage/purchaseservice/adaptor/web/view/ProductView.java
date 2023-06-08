package com.blockpage.purchaseservice.adaptor.web.view;

import com.blockpage.purchaseservice.adaptor.infrastructure.mysql.value.NftType;
import com.blockpage.purchaseservice.adaptor.infrastructure.mysql.value.ProductType;
import com.blockpage.purchaseservice.application.service.ProductService.ProductEntityDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductView {

    private String message;

    private Long nftId;
    private String nftMemberId;
    private String nftCreatorId;
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


    public ProductView(ProductEntityDto dto) {
        if (dto.getProductType() == ProductType.NFT) {
            this.nftId = dto.getNftDto().getNftId();
            this.nftMemberId = dto.getNftDto().getNftMemberId();
            this.nftCreatorId = dto.getNftDto().getNftCreatorId();
            this.nftName = dto.getNftDto().getNftName();
            this.nftDescription = dto.getNftDto().getNftDescription();
            this.nftBlockPrice = dto.getNftDto().getNftBlockPrice();
            this.nftImage = dto.getNftDto().getNftImage();
            this.nftType = dto.getNftDto().getNftType();
        } else {
            this.profileSkinId = dto.getProfileSkinDto().getProfileSkinId();
            this.profileSkinName = dto.getProfileSkinDto().getProfileSkinName();
            this.profileSkinDescription = dto.getProfileSkinDto().getProfileSkinDescription();
            this.profileSkinBlockPrice = dto.getProfileSkinDto().getProfileSkinBlockPrice();
            this.profileSkinImage = dto.getProfileSkinDto().getProfileSkinImage();
        }
    }

    public ProductView(String message) {
        this.message = message;
    }
}
