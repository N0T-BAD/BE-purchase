package com.blockpage.purchaseservice.adaptor.infrastructure.external.block.controller;


import com.blockpage.purchaseservice.adaptor.infrastructure.external.block.configuration.BlockServiceFeignConfig;
import com.blockpage.purchaseservice.adaptor.infrastructure.external.block.requestbody.BlockPayRequestParams;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
    name = "block-service", url = "${block.service.api.url}",
    configuration = BlockServiceFeignConfig.class,
    fallback = BlockServiceFeign.FallBack.class
)
public interface BlockServiceFeign {

    @PutMapping(value = "/v1/blocks")
    ResponseEntity blockPay(@RequestBody BlockPayRequestParams blockPayRequestParams);

    @Component
    class FallBack implements BlockServiceFeign {

        @Override
        public ResponseEntity blockPay(BlockPayRequestParams blockPayRequestParams) {
            return ResponseEntity.status(HttpStatus.OK).build();
        }
    }
}

