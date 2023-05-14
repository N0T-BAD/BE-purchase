package com.blockpage.purchaseservice.adaptor.external.block.controller;

import com.blockpage.purchaseservice.adaptor.external.block.request.BlockPayRequestParams;
import com.blockpage.purchaseservice.application.port.out.BlockServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BlockServiceController implements BlockServicePort {

    private final BlockServiceOpenFeign blockServiceOpenFeign;

    @Override
    public ResponseEntity blockPay(BlockPayRequestParams blockPayRequestParams) {
        return blockServiceOpenFeign.blockPay(blockPayRequestParams);
    }
}
