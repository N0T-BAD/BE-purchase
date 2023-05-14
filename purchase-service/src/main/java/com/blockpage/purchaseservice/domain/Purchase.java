package com.blockpage.purchaseservice.domain;

import static java.lang.Boolean.*;

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

    private final long FREE_POLICY_FOR_YEARS = 100l; //100년
    private final long RENTAL_POLICY_FOR_DAYS = 2l;  //2일

    private ProductType productType;                //도메인만 가지고 있음

    private Long memberId;
    private PersistType persistType;
    private LocalDateTime expiredDate;

    private Long memberHasEpisodeBMId;
    private Long episodeId;
    private Long webtoonId;

    private Long memberHasNftId;
    private NftEntity nftEntity;

    private Long memberHasProfileSkinId;
    private ProfileSkinEntity profileSkinEntity;
    private Boolean profileSkinDefault;

    public LocalDateTime makeExpiredDate(PersistType persistType) {
        switch (persistType) {
            case RENTAL -> this.expiredDate = LocalDateTime.now().plusDays(RENTAL_POLICY_FOR_DAYS);
            case PERMANENT -> this.expiredDate = LocalDateTime.now().plusYears(FREE_POLICY_FOR_YEARS);
            default -> throw new IllegalStateException("Unexpected value: " + persistType);
        }
        return this.expiredDate;
    }

    public void changeProfileSkinDefault(Purchase purchase) {
        if (purchase.profileSkinDefault == TRUE) {
            purchase.profileSkinDefault = FALSE;
        } else {
            purchase.profileSkinDefault = TRUE;
        }
    }

    public static Purchase initPurchaseForSave(PurchaseQuery query) {
        ProductType productType = ProductType.findByValue(query.getProductType());
        switch (productType) {
            case NFT -> {
                return Purchase.builder()
                    .expiredDate(null)
                    .productType(productType)
                    .persistType(PersistType.findByValue(query.getPersistType()))
                    .profileSkinDefault(TRUE)
                    .build();
            }
            case EPISODE_BM -> {
                return Purchase.builder()
                    .expiredDate(null)
                    .productType(productType)
                    .persistType(PersistType.findByValue(query.getPersistType()))
                    .profileSkinDefault(TRUE)
                    .build();
            }
            case PROFILE_SKIN -> {
                return Purchase.builder()
                    .expiredDate(null)
                    .productType(productType)
                    .persistType(PersistType.findByValue(query.getPersistType()))
                    .profileSkinDefault(TRUE)
                    .build();
            }
        }
    }


    enum PersistType {
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

    enum ProductType {
        EPISODE_BM(0, "episode-bm"),
        PROFILE_SKIN(1, "profile-skin"),
        NFT(2, "nft"),
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