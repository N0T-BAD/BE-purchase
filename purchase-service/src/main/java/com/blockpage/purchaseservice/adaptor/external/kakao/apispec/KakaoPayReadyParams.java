package com.blockpage.purchaseservice.adaptor.external.kakao.apispec;

import static com.blockpage.purchaseservice.adaptor.external.kakao.configuration.KakaoPayRedirectUrl.*;

import com.blockpage.purchaseservice.application.port.in.KakaoPayReadyInDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class KakaoPayReadyParams {

    private String cid;
    private Integer tax_free_amount;
    private String partner_order_id;
    private String partner_user_id;
    private String item_name;
    private Integer quantity;
    private Integer total_amount;
    private String approval_url;
    private String cancel_url;
    private String fail_url;

    public static KakaoPayReadyParams addEssentialParams(Long memberId, String orderNumber, KakaoPayReadyInDto kakaoPayReadyInDto) {
        return KakaoPayReadyParams.builder()
            .cid("TC0ONETIME")
            .approval_url(KAKAO_APPROVAL_URL.getUrl())
            .cancel_url(KAKAO_CANCEL_URL.getUrl())
            .fail_url(KAKAO_FAIL_URL.getUrl())
            .tax_free_amount(0)
            .partner_order_id(orderNumber)
            .item_name(kakaoPayReadyInDto.getItemName())
            .quantity(kakaoPayReadyInDto.getQuantity())
            .partner_user_id(memberId.toString())
            .total_amount(kakaoPayReadyInDto.getTotalAmount())
            .build();
    }
}
