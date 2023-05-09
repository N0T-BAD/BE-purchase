package com.blockpage.purchaseservice.adaptor.external.kakao.controller;

import com.blockpage.purchaseservice.adaptor.external.kakao.apispec.KakaoPayReadyParams;
import com.blockpage.purchaseservice.adaptor.external.kakao.apispec.KakaoPayReadyResponse;
import com.blockpage.purchaseservice.application.port.out.KakaoReadyPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KakaoReadyPayFeignClientAdaptor implements KakaoReadyPort {

    private final KakaoPayFeignClient kakaoPayFeignClient;

    @Override
    public KakaoPayReadyResponse ready(KakaoPayReadyParams kakaoPayReadyParams) {
        return kakaoPayFeignClient.ready(kakaoPayReadyParams);
    }
}
