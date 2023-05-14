package com.blockpage.purchaseservice.application.port.in;

import com.blockpage.purchaseservice.adaptor.web.requestbody.PurchaseRequest;
import lombok.Builder;
import lombok.Getter;

public interface PurchaseUseCase {

    void purchaseProduct(PurchaseQuery purchaseQuery);

    @Getter
    @Builder
    class PurchaseQuery {

        private String productType;
        private Integer blockQuantity;

        private Long memberId;

        private Long webtoonId;

        //NFT purchase spec
        private Long nftId;

        //profile-skin purchase spec
        private Long profileSkinId;

        //episodeBM purchase spec
        private Long episodeId;
        private String persistType;

        public static PurchaseQuery toQuery(Long testMemberId, String productType, PurchaseRequest purchaseRequest) {
            return PurchaseQuery.builder()
                .memberId(testMemberId)
                .productType(productType)
                .blockQuantity(purchaseRequest.getBlockQuantity())
                .webtoonId(purchaseRequest.getWebtoonId())
                .nftId(purchaseRequest.getNftId())
                .profileSkinId(purchaseRequest.getProfileSkinId())
                .episodeId(purchaseRequest.getEpisodeId())
                .persistType(purchaseRequest.getPersistType())
                .build();
        }
    }
}
