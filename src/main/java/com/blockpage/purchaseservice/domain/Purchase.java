package com.blockpage.purchaseservice.domain;

import static java.lang.Boolean.*;

import com.blockpage.purchaseservice.adaptor.infrastructure.mysql.entity.MemberHasEpisodeBMEntity;
import com.blockpage.purchaseservice.adaptor.infrastructure.mysql.entity.MemberHasNftEntity;
import com.blockpage.purchaseservice.adaptor.infrastructure.mysql.entity.MemberHasProfileSkinEntity;
import com.blockpage.purchaseservice.adaptor.infrastructure.mysql.entity.NftEntity;
import com.blockpage.purchaseservice.adaptor.infrastructure.mysql.entity.ProfileSkinEntity;
import com.blockpage.purchaseservice.adaptor.infrastructure.mysql.value.NftType;
import com.blockpage.purchaseservice.application.port.in.PurchaseUseCase.PurchaseQuery;

import java.time.LocalDateTime;
import java.util.Arrays;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Purchase {

    private final static long FREE_POLICY_FOR_YEARS = 100l; //100년
    private final static long RENTAL_POLICY_FOR_DAYS = 2l;  //2일

    private ProductType productType;                //도메인만 가지고 있음

    private String memberId;
    private PersistType persistType;
    private LocalDateTime expiredDate;
    private Integer blockQuantity;

    private Long memberHasEpisodeBMId;
    private Long episodeId;
    private Long webtoonId;
    private String webtoonTitle;
    private Integer episodeNumber;
    private String webtoonThumbnail;
    private String creator;
    private String illustrator;
    private String genre;

    private Long memberHasNftId;
    private NftWrapper nftWrapper;

    private Long memberHasProfileSkinId;
    private ProfileSkinWrapper profileSkinWrapper;
    private Boolean profileSkinDefault;

    public static LocalDateTime makeExpiredDate(PersistType persistType) {
        switch (persistType) {
            case RENTAL -> {
                return LocalDateTime.now().plusDays(RENTAL_POLICY_FOR_DAYS);
            }
            case PERMANENT -> {
                return LocalDateTime.now().plusYears(FREE_POLICY_FOR_YEARS);
            }
            default -> throw new IllegalStateException("Unexpected value: " + persistType);
        }
    }

    public static Purchase initPurchaseForSave(PurchaseQuery query) {
        ProductType productType = ProductType.findByValue(query.getProductType());
        PersistType persistType = PersistType.findByValue(query.getPersistType());
        switch (productType) {
            case EPISODE_BM_PAID, EPISODE_BM_FREE -> {
                return Purchase.builder()
                    .memberId(query.getMemberId())
                    .productType(productType)
                    .persistType(persistType)
                    .expiredDate(makeExpiredDate(persistType))
                    .blockQuantity(query.getBlockQuantity())
                    .episodeId(query.getEpisodeId())
                    .webtoonId(query.getWebtoonId())
                    .episodeNumber(query.getEpisodeNumber())
                    .webtoonTitle(query.getWebtoonTitle())
                    .memberHasEpisodeBMId(null)
                    .webtoonThumbnail(query.getWebtoonThumbnail())
                    .creator(query.getCreator())
                    .illustrator(query.getIllustrator())
                    .genre(query.getGenre())
                    .build();
            }
            case NFT -> {
                return Purchase.builder()
                    .memberId(query.getMemberId())
                    .productType(productType)
                    .persistType(persistType)
                    .expiredDate(makeExpiredDate(persistType))
                    .blockQuantity(query.getBlockQuantity())
                    .nftWrapper(NftWrapper.initForSave(query.getNftId()))
                    .memberHasNftId(null)
                    .build();
            }
            case PROFILE_SKIN -> {
                return Purchase.builder()
                    .memberId(query.getMemberId())
                    .productType(productType)
                    .persistType(persistType)
                    .expiredDate(makeExpiredDate(persistType))
                    .blockQuantity(query.getBlockQuantity())
                    .memberHasProfileSkinId(null)
                    .profileSkinWrapper(ProfileSkinWrapper.initForSave(query.getProfileSkinId()))
                    .profileSkinDefault(FALSE)
                    .build();
            }
            default -> throw new RuntimeException("Bad Request");
        }
    }

    public static Purchase toDomainFromMemberNftEntity(MemberHasNftEntity entity) {
        return Purchase.builder()
            .productType(ProductType.NFT)
            .memberId(entity.getMemberId())
            .persistType(PersistType.findByValue(entity.getPersistType().getValue()))
            .expiredDate(entity.getExpiredDate())
            .blockQuantity(entity.getBlockQuantity())
            .memberHasNftId(entity.getId())
            .nftWrapper(NftWrapper.initForGet(entity.getNftEntity()))
            .build();
    }

    public static Purchase toDomainFromMemberProfileSkinEntity(MemberHasProfileSkinEntity entity) {
        return Purchase.builder()
            .productType(ProductType.PROFILE_SKIN)
            .memberId(entity.getMemberId())
            .persistType(PersistType.findByValue(entity.getPersistType().getValue()))
            .expiredDate(entity.getExpiredDate())
            .blockQuantity(entity.getBlockQuantity())
            .memberHasProfileSkinId(entity.getId())
            .profileSkinWrapper(ProfileSkinWrapper.initForGet(entity.getProfileSkinEntity()))
            .profileSkinDefault(entity.getDefaultSkin())
            .build();
    }


    public static Purchase toDomainFromMemberEpisodeBMEntity(MemberHasEpisodeBMEntity entity) {
        return Purchase.builder()
            .productType(entity.getFree() ? ProductType.EPISODE_BM_FREE : ProductType.EPISODE_BM_PAID)
            .memberId(entity.getMemberId())
            .blockQuantity(entity.getBlockQuantity())
            .persistType(PersistType.findByValue(entity.getPersistType().getValue()))
            .expiredDate(entity.getExpiredDate())
            .memberHasEpisodeBMId(entity.getId())
            .episodeId(entity.getEpisodeId())
            .webtoonId(entity.getWebtoonId())
            .episodeNumber(entity.getEpisodeNumber())
            .webtoonTitle(entity.getWebtoonTitle())
            .webtoonThumbnail(entity.getWebtoonThumbnail())
            .creator(entity.getCreator())
            .illustrator(entity.getIllustrator())
            .genre(entity.getGenre())
            .build();
    }

    @Builder
    @Getter
    public static class NftWrapper {

        private Long id;
        private String memberId;
        private String creatorId;
        private String nftName;
        private String nftDescription;
        private Integer nftBlockPrice;
        private String nftImage;
        private NftType nftType;

        public static NftWrapper initForGet(NftEntity entity) {
            return NftWrapper.builder()
                .id(entity.getId())
                .memberId(entity.getMemberId())
                .creatorId(entity.getCreatorId())
                .nftName(entity.getNftName())
                .nftBlockPrice(entity.getNftBlockPrice())
                .nftImage(entity.getNftImage())
                .nftType(entity.getNftType())
                .build();
        }

        public static NftWrapper initForSave(Long nftId) {
            return NftWrapper.builder()
                .id(nftId)
                .build();
        }
    }

    @Getter
    @Builder
    public static class ProfileSkinWrapper {

        private Long id;
        private String profileSkinName;
        private String profileSkinDescription;
        private String profileSkinBlockPrice;
        private String profileSkinImage;

        public static ProfileSkinWrapper initForGet(ProfileSkinEntity entity) {
            return ProfileSkinWrapper.builder()
                .id(entity.getId())
                .profileSkinName(entity.getProfileSkinName())
                .profileSkinDescription(entity.getProfileSkinDescription())
                .profileSkinBlockPrice(entity.getProfileSkinBlockPrice())
                .profileSkinImage(entity.getProfileSkinImage())
                .build();
        }

        public static ProfileSkinWrapper initForSave(Long profileSkinId) {
            return ProfileSkinWrapper.builder()
                .id(profileSkinId)
                .build();
        }
    }

    public enum PersistType {
        PERMANENT(0, "permanent"),
        RENTAL(1, "rental"),
        ;
        private int key;
        private String value;

        PersistType(int key, String value) {
            this.key = key;
            this.value = value;
        }

        public int getKey() {
            return key;
        }

        public String getValue() {
            return value;
        }

        public static PersistType findByValue(String value) {
            return Arrays.stream(PersistType.values())
                .filter(t -> t.getValue().equals(value))
                .findFirst()
                .get();
        }
    }

    public enum ProductType {
        EPISODE_BM_PAID(0, "episodeBMPaid"),
        EPISODE_BM_FREE(1, "episodeBMFree"),
        PROFILE_SKIN(2, "profileSkin"),
        NFT(3, "nft"),
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