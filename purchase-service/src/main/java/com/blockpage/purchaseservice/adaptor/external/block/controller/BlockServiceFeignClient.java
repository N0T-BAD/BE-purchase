package com.blockpage.purchaseservice.adaptor.external.block.controller;


import com.blockpage.purchaseservice.adaptor.external.block.configuration.BlockServiceFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;


@FeignClient(name = "block-service", url = "${block.service.api.url}", configuration = BlockServiceFeignConfig.class)
public interface BlockServiceFeignClient {

}

