package com.blockpage.purchaseservice.adaptor.web.view;

import com.blockpage.purchaseservice.adaptor.infrastructure.WebtoonBMEntity;
import com.blockpage.purchaseservice.adaptor.infrastructure.WebtoonBMStatus;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class WebtoonBMDetailView {

    private Long id;

    private Long webtoonId;

    private Long episodeId;

    private Integer webtoonBMBlockPrice;

    private WebtoonBMStatus webtoonBMStatus;

    public static WebtoonBMDetailView toViewFromEntity(WebtoonBMEntity webtoonBM) {
        return WebtoonBMDetailView.builder()
            .id(webtoonBM.getId())
            .webtoonId(webtoonBM.getWebtoonId())
            .episodeId(webtoonBM.getEpisodeId())
            .webtoonBMBlockPrice(webtoonBM.getWebtoonBMBlockPrice())
            .webtoonBMStatus(webtoonBM.getWebtoonBMStatus())
            .build();
    }
}
