package com.blockpage.purchaseservice.adaptor.external.kakao.configuration;

import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KakaoPayFeignConfig {

    @Value("${kakao.pay.api.key}")
    private String kakaoPayApiKey;

    @Value("${kakao.pay.api.content-type}")
    private String kakaoPayApiContentType;

    @Bean
    public RequestInterceptor requestInterceptor() {
        return new KakaoPayFeignClientHeaderInterceptor(kakaoPayApiKey, kakaoPayApiContentType);
    }
}
