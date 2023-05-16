package com.blockpage.purchaseservice.application.service;

import com.blockpage.purchaseservice.adaptor.infrastructure.mysql.entity.NftEntity;
import com.blockpage.purchaseservice.adaptor.infrastructure.mysql.entity.ProfileSkinEntity;
import com.blockpage.purchaseservice.adaptor.infrastructure.mysql.value.ProductType;
import com.blockpage.purchaseservice.application.port.in.ProductUseCase;
import com.blockpage.purchaseservice.application.port.out.ProductPersistencePort;
import com.blockpage.purchaseservice.application.service.PurchaseService.NftDto;
import com.blockpage.purchaseservice.application.service.PurchaseService.ProfileSkinDto;
import com.blockpage.purchaseservice.domain.Product;
import com.blockpage.purchaseservice.domain.Product.Nft;
import com.blockpage.purchaseservice.domain.Product.NftType;
import com.blockpage.purchaseservice.domain.Product.ProfileSkin;
import java.util.List;
import java.util.stream.Collectors;
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
    public List<ProductDto> productQuery(ProductQuery query) {
        List<Product> productList;
        switch (ProductType.findByValue(query.getType())) {
            case NFT -> productList = productPersistencePort.findAllNft();
            case PROFILE_SKIN -> productList = productPersistencePort.findAllProfileSkin();
            default -> throw new IllegalStateException("Unexpected value");
        }

        return productList.stream()
            .map(ProductDto::toDto)
            .collect(Collectors.toList());
    }

    @Getter
    @Builder
    public static class ProductDto {

        private ProductType productType;
        private NftDto nftDto;
        private ProfileSkinDto profileSkinDto;

        public static ProductDto toDto(Product product) {
            return ProductDto.builder()
                .productType(ProductType.findByValue(product.getProductType().getValue()))
                .nftDto(product.getNft() != null ? NftDto.toDto(product.getNft()) : null)
                .profileSkinDto(product.getProfileSkin() != null ? ProfileSkinDto.toDto(product.getProfileSkin()) : null)
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

        public static NftDto toDto(Nft nft) {
            return NftDto.builder()
                .nftId(nft.getNftId())
                .nftMemberId(nft.getNftMemberId())
                .nftCreatorId(nft.getNftCreatorId())
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

        public static ProfileSkinDto toDto(ProfileSkin profileSkin) {
            return ProfileSkinDto.builder()
                .profileSkinId(profileSkin.getProfileSkinId())
                .profileSkinName(profileSkin.getProfileSkinName())
                .profileSkinDescription(profileSkin.getProfileSkinDescription())
                .profileSkinBlockPrice(profileSkin.getProfileSkinBlockPrice())
                .profileSkinImage(profileSkin.getProfileSkinImage())
                .build();
        }
    }
}
