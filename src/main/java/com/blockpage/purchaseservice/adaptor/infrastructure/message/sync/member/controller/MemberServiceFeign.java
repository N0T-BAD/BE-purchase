package com.blockpage.purchaseservice.adaptor.infrastructure.message.sync.member.controller;

import com.blockpage.purchaseservice.adaptor.infrastructure.message.sync.member.configuration.MemberServiceFeignConfig;
import com.blockpage.purchaseservice.adaptor.infrastructure.message.sync.member.requestbody.ChangeProfileSkinRequestBody;
import com.blockpage.purchaseservice.adaptor.infrastructure.message.sync.member.requestbody.ChangeProfileSkinRequestParams;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(
    name = "member-service",
    configuration = MemberServiceFeignConfig.class,
    fallback = MemberServiceFeign.FallBack.class
)
public interface MemberServiceFeign {

    @PutMapping(value = "/member-service/v1/members")
    ResponseEntity changeProfileSkin(@RequestHeader String memberId,
        @SpringQueryMap ChangeProfileSkinRequestParams changeProfileSkinRequestParams,
        @RequestBody ChangeProfileSkinRequestBody changeProfileSkinRequestBody);

    @Component
    class FallBack implements MemberServiceFeign {

        @Override
        public ResponseEntity changeProfileSkin(String memberId, ChangeProfileSkinRequestParams changeProfileSkinRequestParams,
            ChangeProfileSkinRequestBody changeProfileSkinRequestBody) {
            return null;
        }
    }
}
