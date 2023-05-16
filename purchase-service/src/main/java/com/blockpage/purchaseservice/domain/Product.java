package com.blockpage.purchaseservice.domain;

import com.blockpage.purchaseservice.adaptor.infrastructure.mysql.entity.NftEntity;
import com.blockpage.purchaseservice.adaptor.infrastructure.mysql.entity.ProfileSkinEntity;
import java.util.Arrays;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    private ProductType productType;
    private Nft nft;
    private ProfileSkin profileSkin;

    @Getter
    @Builder
    public static class Nft {

        private Long nftId;
        private Long nftMemberId;
        private Long nftCreatorId;
        private String nftName;
        private String nftDescription;
        private Integer nftBlockPrice;
        private String nftImage;
        private NftType nftType;

        public static Nft toDetail(NftEntity entity) {
            return Nft.builder()
                .nftId(entity.getId())
                .nftMemberId(entity.getMemberId())
                .nftCreatorId(entity.getCreatorId())
                .nftName(entity.getNftName())
                .nftDescription(entity.getNftDescription())
                .nftBlockPrice(entity.getNftBlockPrice())
                .nftImage(entity.getNftImage())
                .nftType(NftType.findByValue(entity.getNftType().getValue()))
                .build();
        }
    }

    @Getter
    @Builder
    public static class ProfileSkin {

        private Long profileSkinId;
        private String profileSkinName;
        private String profileSkinDescription;
        private String profileSkinBlockPrice;
        private String profileSkinImage;

        public static ProfileSkin toDetail(ProfileSkinEntity entity) {
            return ProfileSkin.builder()
                .profileSkinId(entity.getId())
                .profileSkinName(entity.getProfileSkinName())
                .profileSkinDescription(entity.getProfileSkinDescription())
                .profileSkinBlockPrice(entity.getProfileSkinBlockPrice())
                .profileSkinImage(entity.getProfileSkinImage())
                .build();
        }
    }


    public static Product toDomainFromNftEntity(NftEntity nftEntity) {
        return Product.builder()
            .productType(ProductType.NFT)
            .nft(Nft.toDetail(nftEntity))
            .build();
    }

    public static Product toDomainFromProfileSkinEntity(ProfileSkinEntity profileSkinEntity) {
        return Product.builder()
            .productType(ProductType.PROFILE_SKIN)
            .profileSkin(ProfileSkin.toDetail(profileSkinEntity))
            .build();
    }


    public enum NftType {

        ONE_BLOCK_DISCOUNT_BY_AUTHOR(0, "작가별 블럭 1개 할인권"),
        TWO_BLOCK_DISCOUNT_BY_AUTHOR(1, "작가별 블럭 2개 할인권"),
        ;
        private int key;
        private String value;

        NftType(int key, String value) {
            this.key = key;
            this.value = value;
        }

        public int getKey() {
            return key;
        }

        public String getValue() {
            return value;
        }

        public static NftType findByValue(String value) {
            return Arrays.stream(NftType.values())
                .filter(t -> t.getValue().equals(value))
                .findFirst()
                .get();
        }
    }

    public enum ProductType {

        NFT(0, "nft"),
        PROFILE_SKIN(1, "profile-skin"),
        ;
        private int key;
        private String value;

        ProductType(int key, String value) {
            this.key = key;
            this.value = value;
        }

        public int getKey() {
            return key;
        }

        public String getValue() {
            return value;
        }

        public static ProductType findByValue(String value) {
            return Arrays.stream(ProductType.values())
                .filter(t -> t.getValue().equals(value))
                .findFirst()
                .get();
        }
    }
}
