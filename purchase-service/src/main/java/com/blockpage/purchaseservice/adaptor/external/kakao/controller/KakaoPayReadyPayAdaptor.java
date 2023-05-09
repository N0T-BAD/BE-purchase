package com.blockpage.purchaseservice.adaptor.external.kakao.controller;

import com.blockpage.purchaseservice.adaptor.external.kakao.apispec.KakaoPayReadyParams;
import com.blockpage.purchaseservice.adaptor.external.kakao.apispec.KakaoPayReadyResponse;
import com.blockpage.purchaseservice.application.port.out.KakaoPayReadyPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KakaoPayReadyPayAdaptor implements KakaoPayReadyPort {

    private final KakaoPayFeignClient kakaoPayFeignClient;

    @Override
    public KakaoPayReadyResponse ready(KakaoPayReadyParams kakaoPayReadyParams) {
        return kakaoPayFeignClient.ready(kakaoPayReadyParams);
    }
}
