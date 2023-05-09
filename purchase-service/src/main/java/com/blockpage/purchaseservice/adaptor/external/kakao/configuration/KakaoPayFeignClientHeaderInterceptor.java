package com.blockpage.purchaseservice.adaptor.external.kakao.configuration;

import feign.RequestInterceptor;
import feign.RequestTemplate;

public class KakaoPayFeignClientHeaderInterceptor implements RequestInterceptor {

    private final String KAKAO_PAY_AUTHORIZATION = "Authorization";
    private final String KAKAO_PAY_CONTENT_TYPE = "Content-Type";
    private final String KAKAO_PAY_AUTHORIZATION_PREFIX = "KakaoAK ";

    private String kakaoPayApiKey;
    private String kakaoPayApiContentType;

    public KakaoPayFeignClientHeaderInterceptor(String kakaoPayApiKey, String kakaoPayApiContentType) {
        this.kakaoPayApiKey = kakaoPayApiKey;
        this.kakaoPayApiContentType = kakaoPayApiContentType;
    }

    @Override
    public void apply(RequestTemplate template) {
        template.header(KAKAO_PAY_AUTHORIZATION, KAKAO_PAY_AUTHORIZATION_PREFIX + kakaoPayApiKey);
        template.header(KAKAO_PAY_CONTENT_TYPE, kakaoPayApiContentType);
    }
}
