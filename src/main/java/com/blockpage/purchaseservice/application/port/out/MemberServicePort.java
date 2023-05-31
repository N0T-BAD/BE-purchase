package com.blockpage.purchaseservice.application.port.out;

import com.blockpage.purchaseservice.adaptor.infrastructure.message.sync.member.requestbody.ChangeProfileSkinRequestBody;
import com.blockpage.purchaseservice.adaptor.infrastructure.message.sync.member.requestbody.ChangeProfileSkinRequestParams;
import com.blockpage.purchaseservice.application.port.in.PurchaseUseCase.ChangePurchaseQuery;
import org.springframework.http.ResponseEntity;

public interface MemberServicePort {

    ResponseEntity changeProfileSkin(ChangePurchaseQuery query, ChangeProfileSkinRequestParams params, ChangeProfileSkinRequestBody body);
}
