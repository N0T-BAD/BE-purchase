package com.blockpage.purchaseservice.adaptor.infrastructure.message.member.configuration;

import feign.RequestInterceptor;
import feign.RequestTemplate;

public class MemberServiceFeignInterceptor implements RequestInterceptor {

    private final String CONTENT_TYPE = "Content-Type";

    private String memberServiceContentType;

    public MemberServiceFeignInterceptor(String memberServiceContentType) {
        this.memberServiceContentType = memberServiceContentType;
    }

    @Override
    public void apply(RequestTemplate template) {
        template.header(CONTENT_TYPE, memberServiceContentType);
    }
}
