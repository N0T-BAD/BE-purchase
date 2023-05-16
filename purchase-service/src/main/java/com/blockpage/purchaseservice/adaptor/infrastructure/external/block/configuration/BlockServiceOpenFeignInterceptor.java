package com.blockpage.purchaseservice.adaptor.infrastructure.external.block.configuration;

import feign.RequestInterceptor;
import feign.RequestTemplate;

public class BlockServiceOpenFeignInterceptor implements RequestInterceptor {

    private final String CONTENT_TYPE = "Content-Type";

    private String blockServiceContentType;

    public BlockServiceOpenFeignInterceptor(String blockServiceContentType) {
        this.blockServiceContentType = blockServiceContentType;
    }

    @Override
    public void apply(RequestTemplate template) {
        template.header(CONTENT_TYPE, blockServiceContentType);
    }
}
