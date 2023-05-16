package com.blockpage.purchaseservice.adaptor.infrastructure.external.block.controller;


import com.blockpage.purchaseservice.adaptor.infrastructure.external.block.configuration.BlockServiceFeignConfig;
import com.blockpage.purchaseservice.adaptor.infrastructure.external.block.request.BlockPayRequestParams;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@FeignClient(name = "block-service", url = "${block.service.api.url}", configuration = BlockServiceFeignConfig.class)
public interface BlockServiceOpenFeign {

    @PutMapping(value = "/v1/blocks")
    ResponseEntity blockPay(@RequestBody BlockPayRequestParams blockPayRequestParams);
}

