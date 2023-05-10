package com.blockpage.purchaseservice.application.port.out;

import com.blockpage.purchaseservice.adaptor.infrastructure.mysql.value.PersistType;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PurchaseOutDto {

    //common
    private Long memberId;
    private PersistType persistType;
    private LocalDateTime expiredDate;

    //episodeBM
    private Long episodeId;
    private Long webtoonId;


    public PurchaseOutDto(Long memberId, PersistType persistType, LocalDateTime expiredDate) {
        this.memberId = memberId;
        this.persistType = persistType;
        this.expiredDate = expiredDate;
    }

    public PurchaseOutDto(Long memberId, Long episodeId, Long webtoonId, PersistType persistType, LocalDateTime expiredDate) {
        this.memberId = memberId;
        this.episodeId = episodeId;
        this.webtoonId = webtoonId;
        this.persistType = persistType;
        this.expiredDate = expiredDate;
    }
}
