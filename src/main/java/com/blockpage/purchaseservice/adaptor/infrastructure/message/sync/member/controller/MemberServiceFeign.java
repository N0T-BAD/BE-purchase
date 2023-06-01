package com.blockpage.purchaseservice.adaptor.infrastructure.message.sync.member.controller;

import com.blockpage.purchaseservice.adaptor.infrastructure.message.sync.member.configuration.MemberServiceFeignConfig;
import com.blockpage.purchaseservice.adaptor.infrastructure.message.sync.member.requestbody.ChangeProfileSkinRequestParams;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
    name = "member-service",
    configuration = MemberServiceFeignConfig.class,
    fallback = MemberServiceFeign.FallBack.class
)
public interface MemberServiceFeign {

    @PutMapping(value = "/member-service/v1/members?type=profileSkin")
    ResponseEntity changeProfileSkin(@RequestHeader String memberId,
//        @SpringQueryMap ChangeProfileSkinRequestParams changeProfileSkinRequestParams,
        @RequestParam String profileSkin);

    @Component
    class FallBack implements MemberServiceFeign {

        @Override
        public ResponseEntity changeProfileSkin(String memberId,
            String profileSkin) {
            return null;
        }
    }
}
