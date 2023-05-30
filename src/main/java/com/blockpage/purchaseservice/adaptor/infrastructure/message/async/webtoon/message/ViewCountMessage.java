package com.blockpage.purchaseservice.adaptor.infrastructure.message.async.webtoon.message;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ViewCountMessage {

    private Long webtoonId;
    private Long viewCount;

    public static ViewCountMessage initMessage(Long webtoonId, Long viewCount) {
        return ViewCountMessage.builder()
            .webtoonId(webtoonId)
            .viewCount(viewCount)
            .build();
    }
}
