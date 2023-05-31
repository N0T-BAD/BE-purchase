package com.blockpage.purchaseservice.adaptor.infrastructure.message.sync.member.controller;

import com.blockpage.purchaseservice.adaptor.infrastructure.message.sync.member.requestbody.ChangeProfileSkinRequestBody;
import com.blockpage.purchaseservice.adaptor.infrastructure.message.sync.member.requestbody.ChangeProfileSkinRequestParams;
import com.blockpage.purchaseservice.application.port.in.PurchaseUseCase.ChangePurchaseQuery;
import com.blockpage.purchaseservice.application.port.out.MemberServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemberServiceController implements MemberServicePort {

    private final MemberServiceFeign memberServiceFeign;

    @Override
    public ResponseEntity changeProfileSkin(ChangePurchaseQuery query, ChangeProfileSkinRequestParams params,
        ChangeProfileSkinRequestBody body) {
        return memberServiceFeign.changeProfileSkin(query.getMemberId(), params, body);
    }
}
