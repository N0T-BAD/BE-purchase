package com.blockpage.purchaseservice.adaptor.web.requestbody;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MemberPurchaseRequest {

    //NFT purchase spec
    private Long nftId;

    //profile-skin purchase spec
    private Long profileSkinId;

    //episodeBM purchase spec
    private Long episodeId;
    private Long webtoonId;
    private String persistType;
}