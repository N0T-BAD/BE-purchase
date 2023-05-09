package com.blockpage.purchaseservice.application.port.in;

import com.blockpage.purchaseservice.adaptor.external.kakao.apispec.KakaoPayReadyRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class KakaoPayInPortDto {

    private Long memberId;
    private String itemName;
    private Integer quantity;
    private Integer totalAmount;

    public static KakaoPayInPortDto toInPortDto(Long memberId, KakaoPayReadyRequest kakaoPayReadyRequest) {
        return KakaoPayInPortDto.builder()
            .memberId(memberId)
            .itemName(kakaoPayReadyRequest.getItemName())
            .quantity(kakaoPayReadyRequest.getQuantity())
            .totalAmount(kakaoPayReadyRequest.getTotalAmount())
            .build();
    }
}