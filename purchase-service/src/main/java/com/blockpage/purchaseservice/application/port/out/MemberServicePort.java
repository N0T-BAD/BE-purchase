package com.blockpage.purchaseservice.application.port.out;

import com.blockpage.purchaseservice.adaptor.infrastructure.external.member.requestbody.ChangeProfileSkinRequestBody;
import com.blockpage.purchaseservice.adaptor.infrastructure.external.member.requestbody.ChangeProfileSkinRequestParams;
import org.springframework.http.ResponseEntity;

public interface MemberServicePort {

    ResponseEntity changeProfileSkin(ChangeProfileSkinRequestParams params, ChangeProfileSkinRequestBody body);
}
