package com.blockpage.purchaseservice.application.port.in;

import com.blockpage.purchaseservice.adaptor.web.apispec.MemberPurchaseRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class PurchaseInPortDto {

    private String type;

    private Long memberId;

    private Long webtoonId;

    //NFT purchase spec
    private Long nftId;

    //profile-skin purchase spec
    private Long profileSkinId;

    //episodeBM purchase spec
    private Long episodeId;
    private String persistType;

    public static PurchaseInPortDto toDto(String type, Long memberId, MemberPurchaseRequest memberPurchaseRequest) {
        return PurchaseInPortDto.builder()
            .type(type)
            .memberId(memberId)
            .nftId(memberPurchaseRequest.getNftId())
            .profileSkinId(memberPurchaseRequest.getProfileSkinId())
            .webtoonId(memberPurchaseRequest.getWebtoonId())
            .episodeId(memberPurchaseRequest.getEpisodeId())
            .persistType(memberPurchaseRequest.getPersistType())
            .build();
    }
}
