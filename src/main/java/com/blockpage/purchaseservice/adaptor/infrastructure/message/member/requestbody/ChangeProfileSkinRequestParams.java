package com.blockpage.purchaseservice.adaptor.infrastructure.message.member.requestbody;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class ChangeProfileSkinRequestParams {

    private String type;

    public static ChangeProfileSkinRequestParams addEssentialParams(String type) {
        return ChangeProfileSkinRequestParams.builder()
            .type(type)
            .build();
    }
}
