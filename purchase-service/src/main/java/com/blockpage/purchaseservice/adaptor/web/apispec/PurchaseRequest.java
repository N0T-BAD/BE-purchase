package com.blockpage.purchaseservice.adaptor.web.apispec;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PurchaseRequest {

    /**
     * NFT purchase spec
     */
    private Long nftId;

    /**
     * profile-skin purchase spec
     */
    private Long profileSkinId;
    private Boolean defaultSkin;

    /**
     * episodeBM purchase spec
     */
    private Long episodeId;
    private String persistType;
    private LocalDateTime expiredDate;

    /**
     * common spec
     */
    private Long memberId;
}


