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

    private final static long FREE_POLICY_FOR_YEARS = 100l; //100년
    private final static long RENTAL_POLICY_FOR_DAYS = 2l;  //2일

    private ProductType productType;                //도메인만 가지고 있음

    private Long memberId;
    private PersistType persistType;
    private LocalDateTime expiredDate;

    private Long memberHasEpisodeBMId;
    private Long episodeId;
    private Long webtoonId;

    private Long memberHasNftId;
    private NftFkWrapper nftFk;

    private Long memberHasProfileSkinId;
    private ProfileSkinFkWrapper profileSkinFk;
    private Boolean profileSkinDefault;

    public static Purchase initPurchaseForSave(PurchaseQuery query) {
        ProductType productType = ProductType.findByValue(query.getProductType());
        PersistType persistType = PersistType.findByValue(query.getPersistType());
        switch (productType) {
            case EPISODE_BM -> {
                return Purchase.builder()
                    .memberId(query.getMemberId())
                    .productType(productType)
                    .persistType(persistType)
                    .expiredDate(makeExpiredDate(persistType))
                    .episodeId(query.getEpisodeId())
                    .webtoonId(query.getWebtoonId())
                    .memberHasEpisodeBMId(null)
                    .build();
            }
            case NFT -> {
                return Purchase.builder()
                    .memberId(query.getMemberId())
                    .productType(productType)
                    .persistType(persistType)
                    .expiredDate(makeExpiredDate(persistType))
                    .nftFk(new NftFkWrapper(query.getNftId()))
                    .memberHasNftId(null)
                    .build();
            }
            case PROFILE_SKIN -> {
                return Purchase.builder()
                    .memberId(query.getMemberId())
                    .productType(productType)
                    .persistType(persistType)
                    .expiredDate(makeExpiredDate(persistType))
                    .memberHasProfileSkinId(null)
                    .profileSkinFk(new ProfileSkinFkWrapper(query.getProfileSkinId()))
                    .profileSkinDefault(FALSE)
                    .build();
            }
            default -> throw new RuntimeException("Bad Request");
        }
    }

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

    public void changeProfileSkinDefault(Purchase purchase) {
        if (purchase.profileSkinDefault == TRUE) {
            purchase.profileSkinDefault = FALSE;
        } else {
            purchase.profileSkinDefault = TRUE;
        }
    }

    public static class NftFkWrapper {

        private Long id;

        public Long getId() {
            return id;
        }

        public NftFkWrapper(Long id) {
            this.id = id;
        }
    }

    public static class ProfileSkinFkWrapper {

        private Long id;

        public Long getId() {
            return id;
        }

        public ProfileSkinFkWrapper(Long id) {
            this.id = id;
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