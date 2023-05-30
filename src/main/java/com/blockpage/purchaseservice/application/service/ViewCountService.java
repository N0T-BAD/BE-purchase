package com.blockpage.purchaseservice.application.service;

import com.blockpage.purchaseservice.adaptor.infrastructure.message.async.webtoon.message.ViewCountMessage;
import com.blockpage.purchaseservice.application.port.out.PurchasePersistencePort;
import com.blockpage.purchaseservice.application.port.out.ViewCountMessagePort;
import com.blockpage.purchaseservice.domain.Purchase;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ViewCountService {

    private final ViewCountMessagePort viewCountMessagePort;
    private final PurchasePersistencePort purchasePersistencePort;

    private final long FIXED_RATE_SECONDS = 5 * 60l;
    private final long FIXED_RATE_MILLISECONDS = 5 * 60 * 1000l; //5분
    private final long INITIAL_DELAY_MILLISECONDS = 3 * 1000l;


    @Scheduled(fixedRate = FIXED_RATE_MILLISECONDS, initialDelay = INITIAL_DELAY_MILLISECONDS)
    public void sendViewCount() {
        LocalDateTime now = LocalDateTime.now();
        List<Purchase> purchases = purchasePersistencePort.findEpisodeBMByCreateDate(now.minusSeconds(FIXED_RATE_SECONDS), now);
        Map<Long, Long> webtoonIdAndViewCounts = purchases.stream()
            .collect(Collectors.groupingBy(Purchase::getWebtoonId, Collectors.counting()));

        if (!webtoonIdAndViewCounts.isEmpty()) {
            for (Long key : webtoonIdAndViewCounts.keySet()) {
                log.info("조회수 카프카 발송 : 웹툰 id=" + key + "조회수=" + webtoonIdAndViewCounts.get(key));
                viewCountMessagePort.sendViewCount(ViewCountMessage.initMessage(key, webtoonIdAndViewCounts.get(key)));
            }
        } else {
            log.info("업데이트된 조회수가 존재하지 않습니다");
        }
    }
}
