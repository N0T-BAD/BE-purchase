package com.blockpage.purchaseservice.adaptor.web.requestbody;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PurchaseRequest {

    //common spec
    private Integer blockQuantity;
    private String persistType;

    //NFT purchase spec
    private Long nftId;

    //profile-skin purchase spec
    private Long profileSkinId;
    private Long memberProfileSkinId;

    //episodeBM purchase spec
    private Long episodeId;
    private String webtoonTitle;
    private Integer episodeNumber;
}