package com.blockpage.purchaseservice.application.port.out;

import com.blockpage.purchaseservice.adaptor.infrastructure.message.block.requestbody.BlockPayRequestParams;
import org.springframework.http.ResponseEntity;

public interface BlockServicePort {

    ResponseEntity blockPay(String memberId, BlockPayRequestParams blockPayRequestParams);
}
