package com.blockpage.purchaseservice.adaptor.infrastructure.message.member.controller;

import com.blockpage.purchaseservice.adaptor.infrastructure.message.member.requestbody.ChangeProfileSkinRequestBody;
import com.blockpage.purchaseservice.adaptor.infrastructure.message.member.requestbody.ChangeProfileSkinRequestParams;
import com.blockpage.purchaseservice.application.port.out.MemberServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemberServiceController implements MemberServicePort {

    private final MemberServiceFeign memberServiceFeign;

    @Override
    public ResponseEntity changeProfileSkin(ChangeProfileSkinRequestParams params, ChangeProfileSkinRequestBody body) {
        return memberServiceFeign.changeProfileSkin(params, body);
    }
}
