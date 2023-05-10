package com.blockpage.purchaseservice.application.port.in;

import com.blockpage.purchaseservice.adaptor.web.apispec.KakaoPayApprovalRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class KakaoPayApprovalInDto {

    private Long memberId;
    private String pgToken;

    public static KakaoPayApprovalInDto toDto(Long memberId, KakaoPayApprovalRequest kakaoPayApprovalRequest) {
        return KakaoPayApprovalInDto.builder()
            .memberId(memberId)
            .pgToken(kakaoPayApprovalRequest.getPgToken())
            .build();
    }
}
