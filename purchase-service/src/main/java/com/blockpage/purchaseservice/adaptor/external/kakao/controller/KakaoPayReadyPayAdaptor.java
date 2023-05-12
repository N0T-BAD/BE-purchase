package com.blockpage.purchaseservice.adaptor.external.kakao.controller;

import com.blockpage.purchaseservice.adaptor.external.kakao.apispec.KakaoPayApprovalParams;
import com.blockpage.purchaseservice.adaptor.external.kakao.apispec.KakaoPayApprovalResponse;
import com.blockpage.purchaseservice.adaptor.external.kakao.apispec.KakaoPayReadyParams;
import com.blockpage.purchaseservice.adaptor.external.kakao.apispec.KakaoPayReadyResponse;
import com.blockpage.purchaseservice.application.port.out.KakaoPayApprovalPort;
import com.blockpage.purchaseservice.application.port.out.KakaoPayReadyPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KakaoPayReadyPayAdaptor implements KakaoPayReadyPort, KakaoPayApprovalPort {

    private final KakaoPayFeignClient kakaoPayFeignClient;

    @Override
    public KakaoPayReadyResponse ready(KakaoPayReadyParams kakaoPayReadyParams) {
        return kakaoPayFeignClient.ready(kakaoPayReadyParams);
    }

    @Override
    public KakaoPayApprovalResponse approval(KakaoPayApprovalParams kakaoPayApprovalParams) {
        return kakaoPayFeignClient.approval(kakaoPayApprovalParams);
    }
}
