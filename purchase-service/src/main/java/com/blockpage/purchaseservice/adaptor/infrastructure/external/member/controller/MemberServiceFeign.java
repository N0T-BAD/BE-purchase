package com.blockpage.purchaseservice.adaptor.infrastructure.external.member.controller;

import com.blockpage.purchaseservice.adaptor.infrastructure.external.member.configuration.MemberServiceFeignConfig;
import com.blockpage.purchaseservice.adaptor.infrastructure.external.member.requestbody.ChangeProfileSkinRequestBody;
import com.blockpage.purchaseservice.adaptor.infrastructure.external.member.requestbody.ChangeProfileSkinRequestParams;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
    name = "member-service",
    url = "${member.service.api.url}",
    configuration = MemberServiceFeignConfig.class,
    fallback = MemberServiceFeign.FallBack.class
)
public interface MemberServiceFeign {

    @PutMapping(value = "/v1/members")
    ResponseEntity changeProfileSkin(@SpringQueryMap
    ChangeProfileSkinRequestParams changeProfileSkinRequestParams, @RequestBody ChangeProfileSkinRequestBody changeProfileSkinRequestBody);

    @Component
    class FallBack implements MemberServiceFeign {

        @Override
        public ResponseEntity changeProfileSkin(ChangeProfileSkinRequestParams changeProfileSkinRequestParams,
            ChangeProfileSkinRequestBody changeProfileSkinRequestBody) {
            return null;
        }
    }
}