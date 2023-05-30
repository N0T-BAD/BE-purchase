package com.blockpage.purchaseservice.adaptor.infrastructure.message.sync.block.controller;

import com.blockpage.purchaseservice.adaptor.infrastructure.message.sync.block.requestbody.BlockPayRequestParams;
import com.blockpage.purchaseservice.application.port.out.BlockServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BlockServiceController implements BlockServicePort {

    private final BlockServiceFeign blockServiceFeign;

    @Override
    public ResponseEntity blockPay(String memberId, BlockPayRequestParams blockPayRequestParams) {
        return blockServiceFeign.blockPay(memberId, blockPayRequestParams);
    }
}
