package com.blockpage.purchaseservice.adaptor.external.kakao.controller;

import com.blockpage.purchaseservice.adaptor.external.kakao.apispec.KakaoPayApprovalParams;
import com.blockpage.purchaseservice.adaptor.external.kakao.apispec.KakaoPayApprovalResponse;
import com.blockpage.purchaseservice.adaptor.external.kakao.configuration.KakaoPayFeignConfig;
import com.blockpage.purchaseservice.adaptor.external.kakao.apispec.KakaoPayReadyResponse;
import com.blockpage.purchaseservice.adaptor.external.kakao.apispec.KakaoPayReadyParams;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.PostMapping;


@FeignClient(name = "kakaopay-service", url = "${kakao.pay.api.url}", configuration = KakaoPayFeignConfig.class)
public interface KakaoPayFeignClient {

    @PostMapping(value = "/v1/payment/ready")
    KakaoPayReadyResponse ready(@SpringQueryMap KakaoPayReadyParams kakaoPayReadyParams);

    @PostMapping(value = "/v1/payment/approve")
    KakaoPayApprovalResponse approval(@SpringQueryMap KakaoPayApprovalParams kakaoPayApprovalParams);
}

