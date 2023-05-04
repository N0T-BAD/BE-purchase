package com.blockpage.purchaseservice.adaptor.web.view;

import com.blockpage.purchaseservice.adaptor.infrastructure.entity.MemberHasWebtoonBMEntity;
import com.blockpage.purchaseservice.adaptor.infrastructure.value.PersistType;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MemberWebtoonBMView {

    private Long id;

    private Long episode_id;

    private PersistType persistType;

    private LocalDateTime expiredDate;

    public static MemberWebtoonBMView toViewFromEntity(MemberHasWebtoonBMEntity memberHasWebtoonBM) {
        return MemberWebtoonBMView.builder()
            .id(memberHasWebtoonBM.getId())
            .episode_id(memberHasWebtoonBM.getEpisodeId())
            .persistType(memberHasWebtoonBM.getPersistType())
            .expiredDate(memberHasWebtoonBM.getExpiredDate())
            .build();
    }
}
