package com.blockpage.purchaseservice.adaptor.infrastructure.external.block.controller;


import com.blockpage.purchaseservice.adaptor.infrastructure.external.block.configuration.BlockServiceOpenFeignConfig;
import com.blockpage.purchaseservice.adaptor.infrastructure.external.block.requestbody.BlockPayRequestParams;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@FeignClient(name = "block-service", url = "${block.service.api.url}", configuration = BlockServiceOpenFeignConfig.class)
public interface BlockServiceOpenFeign {

    @PutMapping(value = "/v1/blocks")
    ResponseEntity blockPay(@RequestBody BlockPayRequestParams blockPayRequestParams);
}

