package com.blockpage.purchaseservice.adaptor.infrastructure.external.member.controller;

import com.blockpage.purchaseservice.adaptor.infrastructure.external.member.requestbody.ChangeProfileSkinRequestBody;
import com.blockpage.purchaseservice.adaptor.infrastructure.external.member.requestbody.ChangeProfileSkinRequestParams;
import com.blockpage.purchaseservice.application.port.out.MemberServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemberServiceController implements MemberServicePort {

    private final MemberServiceOpenFeign memberServiceOpenFeign;

    @Override
    public ResponseEntity changeProfileSkin(ChangeProfileSkinRequestParams params, ChangeProfileSkinRequestBody body) {
        return memberServiceOpenFeign.changeProfileSkin(params, body);
    }
}
