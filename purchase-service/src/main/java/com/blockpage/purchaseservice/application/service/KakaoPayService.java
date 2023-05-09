package com.blockpage.purchaseservice.application.service;

import com.blockpage.purchaseservice.adaptor.external.kakao.apispec.KakaoPayReadyParams;
import com.blockpage.purchaseservice.adaptor.external.kakao.apispec.KakaoPayReadyResponse;
import com.blockpage.purchaseservice.adaptor.web.view.KakaoPayReadyView;
import com.blockpage.purchaseservice.application.port.in.KakaoPayInPortDto;
import com.blockpage.purchaseservice.application.port.in.KakaoPayUseCase;
import com.blockpage.purchaseservice.application.port.out.KakaoReadyPort;
import java.time.LocalDate;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class KakaoPayService implements KakaoPayUseCase {

    private final KakaoReadyPort kakaoReadyPort;

    private final String BUSINESS_REGISTRATION_NUMBER = "TC0ONETIME";
    private final String KAKAO_APPROVAL_URL = "http://localhost:8080/v1/purchases/kakaopay";
    private final String KAKAO_CANCEL_URL = "http://localhost:8080/v1/purchases/kakaopay";
    private final String KAKAO_FAIL_URL = "http://localhost:8080/v1/purchases/kakaopay";
    private final Integer TAX_FREE_AMOUNT = 0;

    @Override
    public KakaoPayReadyView kakaoPayReady(KakaoPayInPortDto kakaoPayInPortDto) {

        KakaoPayReadyParams kakaoPayReadyParams = KakaoPayReadyParams.builder()
            .approval_url(KAKAO_APPROVAL_URL)
            .cancel_url(KAKAO_CANCEL_URL)
            .fail_url(KAKAO_FAIL_URL)
            .cid(BUSINESS_REGISTRATION_NUMBER)
            .tax_free_amount(TAX_FREE_AMOUNT)
            .partner_order_id(createOrderNumber(kakaoPayInPortDto.getMemberId()))
            .item_name(kakaoPayInPortDto.getItemName())
            .quantity(kakaoPayInPortDto.getQuantity())
            .partner_user_id(kakaoPayInPortDto.getMemberId().toString())
            .total_amount(kakaoPayInPortDto.getTotalAmount())
            .build();

        KakaoPayReadyResponse kakaoPayReadyResponse = kakaoReadyPort.ready(kakaoPayReadyParams);
        return new KakaoPayReadyView(kakaoPayReadyResponse.getTid(), kakaoPayReadyResponse.getNext_redirect_pc_url());

    }

    public String createOrderNumber(Long memberId) {
        Random random = new Random();
        random.setSeed(System.currentTimeMillis());

        String yyyyMmDd = LocalDate.now().toString();
        String randomNumbers = Stream.generate(() -> random.nextInt(1, 10))
            .limit(6)
            .map(String::valueOf)
            .collect(Collectors.joining());
        return yyyyMmDd + memberId.toString() + randomNumbers;
    }
}
