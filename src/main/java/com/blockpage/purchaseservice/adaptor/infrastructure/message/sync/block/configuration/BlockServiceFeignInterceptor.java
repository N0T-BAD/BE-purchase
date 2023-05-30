package com.blockpage.purchaseservice.adaptor.infrastructure.message.sync.block.configuration;

import feign.RequestInterceptor;
import feign.RequestTemplate;

public class BlockServiceFeignInterceptor implements RequestInterceptor {

    private final String CONTENT_TYPE = "Content-Type";

    private String blockServiceContentType;

    public BlockServiceFeignInterceptor(String blockServiceContentType) {
        this.blockServiceContentType = blockServiceContentType;
    }

    @Override
    public void apply(RequestTemplate template) {
        template.header(CONTENT_TYPE, blockServiceContentType);
    }
}
