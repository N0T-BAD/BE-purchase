package com.blockpage.purchaseservice.adaptor.infrastructure.external.block.configuration;

import feign.Logger.Level;
import feign.RequestInterceptor;
import feign.Retryer;
import feign.codec.ErrorDecoder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BlockServiceOpenFeignConfig {

    @Value("${block.service.api.content-type}")
    private String blockServiceContentType;

    @Bean
    public feign.Logger.Level feignLoggerLevel() {
        return Level.FULL;
    }

    @Bean
    public ErrorDecoder errorDecoder() {
        return new GlobalErrorDecoder();
    }

    @Bean
    public Retryer retryer() {
        return new Retryer.Default(); //this(100, *SECONDS*.toMillis(1), 5);
    }

    @Bean
    public RequestInterceptor requestInterceptor() {
        return new BlockServiceOpenFeignInterceptor(blockServiceContentType);
    }
}
