package com.blockpage.purchaseservice.application.service;

import com.blockpage.purchaseservice.adaptor.external.kakao.apispec.KakaoPayReadyParams;
import com.blockpage.purchaseservice.adaptor.external.kakao.apispec.KakaoPayReadyResponse;
import com.blockpage.purchaseservice.adaptor.infrastructure.redis.RedisRepository;
import com.blockpage.purchaseservice.adaptor.web.view.KakaoPayReadyView;
import com.blockpage.purchaseservice.application.port.in.KakaoPayInPortDto;
import com.blockpage.purchaseservice.application.port.in.KakaoPayUseCase;
import com.blockpage.purchaseservice.application.port.out.KakaoPayReadyPort;
import com.blockpage.purchaseservice.domain.Payment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class PaymentService implements KakaoPayUseCase {

    private final KakaoPayReadyPort kakaoPayReadyPort;
    private final RedisRepository repository;

    @Override
    public KakaoPayReadyView kakaoPayReady(KakaoPayInPortDto kakaoPayInPortDto) {
        Long memberId = kakaoPayInPortDto.getMemberId();
        Payment payment = new Payment(memberId);
        payment.createOrderNumber();
        KakaoPayReadyParams kakaoPayReadyParams = KakaoPayReadyParams.addEssentialParams(memberId, payment.getOrderNumber(),
            kakaoPayInPortDto);
        KakaoPayReadyResponse kakaoPayReadyResponse = kakaoPayReadyPort.ready(kakaoPayReadyParams);
        return new KakaoPayReadyView(kakaoPayReadyResponse.getTid(), kakaoPayReadyResponse.getNext_redirect_pc_url());
    }
}
