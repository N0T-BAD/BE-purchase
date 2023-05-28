package com.blockpage.purchaseservice.adaptor.infrastructure.message.block.controller;


import com.blockpage.purchaseservice.adaptor.infrastructure.message.block.configuration.BlockServiceFeignConfig;
import com.blockpage.purchaseservice.adaptor.infrastructure.message.block.requestbody.BlockPayRequestParams;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(
    name = "block-service",
    configuration = BlockServiceFeignConfig.class,
    fallback = BlockServiceFeign.FallBack.class
)
public interface BlockServiceFeign {

    @PutMapping(value = "/block-service/v1/blocks")
    ResponseEntity blockPay(@RequestHeader String memberId, @RequestBody BlockPayRequestParams blockPayRequestParams);

    @Component
    class FallBack implements BlockServiceFeign {

        @Override
        public ResponseEntity blockPay(String memberId, BlockPayRequestParams blockPayRequestParams) {
            return ResponseEntity.status(HttpStatus.OK).build();
        }
    }
}

