package com.blockpage.purchaseservice.adaptor.infrastructure.external.member.controller;

import com.blockpage.purchaseservice.adaptor.infrastructure.external.member.configuration.MemberServiceOpenFeignConfig;
import com.blockpage.purchaseservice.adaptor.infrastructure.external.member.requestbody.ChangeProfileSkinRequestBody;
import com.blockpage.purchaseservice.adaptor.infrastructure.external.member.requestbody.ChangeProfileSkinRequestParams;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "member-service", url = "${member.service.api.url}", configuration = MemberServiceOpenFeignConfig.class)
public interface MemberServiceOpenFeign {

    @PutMapping(value = "/v1/members")
    ResponseEntity changeProfileSkin(@SpringQueryMap
    ChangeProfileSkinRequestParams changeProfileSkinRequestParams, @RequestBody ChangeProfileSkinRequestBody changeProfileSkinRequestBody);
}