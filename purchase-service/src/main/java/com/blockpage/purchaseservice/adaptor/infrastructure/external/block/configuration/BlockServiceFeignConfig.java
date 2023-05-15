package com.blockpage.purchaseservice.adaptor.infrastructure.external.block.configuration;

import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BlockServiceFeignConfig {

    @Value("${block.service.api.content-type}")
    private String BlockServiceContentType;

    @Bean
    public RequestInterceptor requestInterceptor() {
        return new BlockServiceFeignClientInterceptor(BlockServiceContentType);
    }
}