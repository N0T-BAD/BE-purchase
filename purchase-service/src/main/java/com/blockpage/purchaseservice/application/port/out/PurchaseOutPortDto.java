package com.blockpage.purchaseservice.application.port.out;

import com.blockpage.purchaseservice.adaptor.infrastructure.value.PersistType;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PurchaseOutPortDto {
    //common
    private Long memberId;

    //episodeBM
    private Long episodeId;
    private Long webtoonId;
    private PersistType persistType;
    private LocalDateTime expiredDate;

    public PurchaseOutPortDto(Long memberId) {
        this.memberId = memberId;
    }

    public PurchaseOutPortDto(Long memberId, Long episodeId, Long webtoonId, PersistType persistType, LocalDateTime expiredDate) {
        this.memberId = memberId;
        this.episodeId = episodeId;
        this.webtoonId = webtoonId;
        this.persistType = persistType;
        this.expiredDate = expiredDate;
    }
}
