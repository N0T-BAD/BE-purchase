package com.blockpage.purchaseservice.application.service;

import com.blockpage.purchaseservice.adaptor.infrastructure.mysql.entity.NftEntity;
import com.blockpage.purchaseservice.adaptor.infrastructure.mysql.entity.ProfileSkinEntity;
import com.blockpage.purchaseservice.adaptor.infrastructure.mysql.value.ProductType;
import com.blockpage.purchaseservice.application.port.in.ProductUseCase;
import com.blockpage.purchaseservice.application.port.out.ProductPersistencePort;
import com.blockpage.purchaseservice.domain.Product;
import com.blockpage.purchaseservice.domain.Product.Nft;
import com.blockpage.purchaseservice.domain.Product.NftType;
import com.blockpage.purchaseservice.domain.Product.ProfileSkin;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductService implements ProductUseCase {

    private final ProductPersistencePort productPersistencePort;

    @Override
    public List<ProductEntityDto> productQuery(ProductQuery query) {
        List<ProductEntityDto> productEntityDtoList;
        switch (ProductType.findByValue(query.getType())) {
            case NFT -> productEntityDtoList = productPersistencePort.findAllNft();
            case PROFILE_SKIN -> productEntityDtoList = productPersistencePort.findAllProfileSkin();
            default -> throw new IllegalStateException("Unexpected value");
        }

        return productEntityDtoList;
    }

    @Getter
    @Builder
    public static class ProductEntityDto {

        private ProductType productType;
        private NftDto nftDto;
        private ProfileSkinDto profileSkinDto;

        public static ProductEntityDto toDtoFromNftEntity(NftEntity entity) {
            return ProductEntityDto.builder()
                .productType(ProductType.NFT)
                .nftDto(NftDto.toDto(entity))
                .build();
        }

        public static ProductEntityDto toDtoFromProfileSkinEntity(ProfileSkinEntity entity) {
            return ProductEntityDto.builder()
                .productType(ProductType.PROFILE_SKIN)
                .profileSkinDto(ProfileSkinDto.toDto(entity))
                .build();
        }
    }

    @Getter
    @Builder
    public static class NftDto {

        private Long nftId;
        private Long nftMemberId;
        private Long nftCreatorId;
        private String nftName;
        private String nftDescription;
        private Integer nftBlockPrice;
        private String nftImage;
        private NftType nftType;

        public static NftDto toDto(NftEntity nft) {
            return NftDto.builder()
                .nftId(nft.getId())
                .nftMemberId(nft.getMemberId())
                .nftCreatorId(nft.getCreatorId())
                .nftName(nft.getNftName())
                .nftDescription(nft.getNftDescription())
                .nftBlockPrice(nft.getNftBlockPrice())
                .nftImage(nft.getNftImage())
                .nftType(NftType.findByValue(nft.getNftType().getValue()))
                .build();
        }
    }

    @Getter
    @Builder
    public static class ProfileSkinDto {

        private Long profileSkinId;
        private String profileSkinName;
        private String profileSkinDescription;
        private String profileSkinBlockPrice;
        private String profileSkinImage;

        public static ProfileSkinDto toDto(ProfileSkinEntity profileSkin) {
            return ProfileSkinDto.builder()
                .profileSkinId(profileSkin.getId())
                .profileSkinName(profileSkin.getProfileSkinName())
                .profileSkinDescription(profileSkin.getProfileSkinDescription())
                .profileSkinBlockPrice(profileSkin.getProfileSkinBlockPrice())
                .profileSkinImage(profileSkin.getProfileSkinImage())
                .build();
        }
    }
}
