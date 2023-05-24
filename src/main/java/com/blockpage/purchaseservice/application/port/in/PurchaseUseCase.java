package com.blockpage.purchaseservice.application.port.in;

import com.blockpage.purchaseservice.adaptor.web.requestbody.PurchaseRequest;
import com.blockpage.purchaseservice.application.service.PurchaseService.PurchaseDto;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

public interface PurchaseUseCase {
    void purchaseProduct(PurchaseQuery purchaseQuery);
    void changeProfileSkinPurchases(ChangePurchaseQuery toQuery);
    List<PurchaseDto> purchaseQuery(FindPurchaseQuery findPurchaseQuery);

    @Getter
    @Builder
    class ChangePurchaseQuery {

        private String memberId;
        private Long memberProfileSkinId;

        public static ChangePurchaseQuery toQuery(String testMemberId, Long memberProfileSkinId) {
            return ChangePurchaseQuery.builder()
                .memberId(testMemberId)
                .memberProfileSkinId(memberProfileSkinId)
                .build();
        }
    }


    @Getter
    @Builder
    class FindPurchaseQuery {

        private String memberId;
        private String productType;
        private Long webtoonId;

        public static FindPurchaseQuery toQuery(String testMemberId, String type, Long webtoonId) {
            return FindPurchaseQuery.builder()
                .memberId(testMemberId)
                .productType(type)
                .webtoonId(webtoonId)
                .build();
        }
    }

    @Getter
    @Builder
    class PurchaseQuery {

        private String productType;
        private String persistType;
        private Integer blockQuantity;
        private String memberId;

        //NFT purchase spec
        private Long nftId;

        //profile-skin purchase spec
        private Long profileSkinId;

        //episodeBM purchase spec
        private Long episodeId;
        private Long webtoonId;

        public static PurchaseQuery toQuery(String testMemberId, String productType, Long webtoonId, PurchaseRequest purchaseRequest) {
            return PurchaseQuery.builder()
                .memberId(testMemberId)
                .productType(productType)
                .blockQuantity(purchaseRequest.getBlockQuantity())
                .webtoonId(webtoonId)
                .nftId(purchaseRequest.getNftId())
                .profileSkinId(purchaseRequest.getProfileSkinId())
                .episodeId(purchaseRequest.getEpisodeId())
                .persistType(purchaseRequest.getPersistType())
                .build();
        }
    }
}
