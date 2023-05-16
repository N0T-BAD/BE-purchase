package com.blockpage.purchaseservice.adaptor.infrastructure.external.member.configuration;

import feign.RequestInterceptor;
import feign.RequestTemplate;

public class MemberServiceOpenFeignInterceptor implements RequestInterceptor {

    private final String CONTENT_TYPE = "Content-Type";

    private String memberServiceContentType;

    public MemberServiceOpenFeignInterceptor(String memberServiceContentType) {
        this.memberServiceContentType = memberServiceContentType;
    }

    @Override
    public void apply(RequestTemplate template) {
        template.header(CONTENT_TYPE, memberServiceContentType);
    }
}
