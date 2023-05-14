package com.blockpage.purchaseservice.adaptor.external.kakao.controller;


import com.blockpage.purchaseservice.adaptor.external.kakao.configuration.KakaoPayFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;


@FeignClient(name = "kakaopay-service", url = "${kakao.pay.api.url}", configuration = KakaoPayFeignConfig.class)
public interface KakaoPayFeignClient {


}

