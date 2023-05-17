package com.blockpage.purchaseservice.adaptor.infrastructure.external.block.controller;


import com.blockpage.purchaseservice.adaptor.infrastructure.external.block.configuration.BlockServiceOpenFeignConfig;
import com.blockpage.purchaseservice.adaptor.infrastructure.external.block.requestbody.BlockPayRequestParams;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "block-service", url = "${block.service.api.url}",
    configuration = BlockServiceOpenFeignConfig.class,
    fallback = BlockServiceOpenFeign.FallBack.class)
public interface BlockServiceOpenFeign {

    @PutMapping(value = "/v1/blocks")
    ResponseEntity blockPay(@RequestBody BlockPayRequestParams blockPayRequestParams);

    @Component
    class FallBack implements BlockServiceOpenFeign {

        @Override
        public ResponseEntity blockPay(BlockPayRequestParams blockPayRequestParams) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).build();
        }
    }

}

