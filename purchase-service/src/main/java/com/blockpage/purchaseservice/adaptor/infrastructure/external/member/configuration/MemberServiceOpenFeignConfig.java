package com.blockpage.purchaseservice.adaptor.infrastructure.external.member.configuration;

import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

public class MemberServiceOpenFeignConfig {

    @Value("${block.service.api.content-type}")
    private String memberServiceContentType;

    @Bean
    public RequestInterceptor requestInterceptor() {
        return new MemberServiceOpenFeignInterceptor(memberServiceContentType);
    }
}
