package com.blockpage.purchaseservice.adaptor.external.block.configuration;

import feign.RequestInterceptor;
import feign.RequestTemplate;

public class BlockServiceFeignClientInterceptor implements RequestInterceptor {

    private final String KAKAO_PAY_CONTENT_TYPE = "Content-Type";

    private String kakaoPayApiContentType;

    public BlockServiceFeignClientInterceptor(String kakaoPayApiContentType) {
        this.kakaoPayApiContentType = kakaoPayApiContentType;
    }

    @Override
    public void apply(RequestTemplate template) {
        template.header(KAKAO_PAY_CONTENT_TYPE, kakaoPayApiContentType);
    }
}
