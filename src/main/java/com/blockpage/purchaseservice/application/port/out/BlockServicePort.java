package com.blockpage.purchaseservice.application.port.out;

import com.blockpage.purchaseservice.adaptor.infrastructure.external.block.requestbody.BlockPayRequestParams;
import org.springframework.http.ResponseEntity;

public interface BlockServicePort {

    ResponseEntity blockPay(BlockPayRequestParams blockPayRequestParams);
}
