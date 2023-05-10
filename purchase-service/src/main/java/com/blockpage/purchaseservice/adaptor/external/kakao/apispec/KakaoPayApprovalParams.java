package com.blockpage.purchaseservice.adaptor.external.kakao.apispec;

import com.blockpage.purchaseservice.application.port.in.KakaoPayApprovalInDto;
import com.blockpage.purchaseservice.application.port.out.PaymentOutDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class KakaoPayApprovalParams {

    private String cid;
    private String tid;
    private String pg_token;
    private String partner_order_id;
    private String partner_user_id;


    public static KakaoPayApprovalParams addEssentialParams(KakaoPayApprovalInDto kakaoPayApprovalINDto, PaymentOutDto paymentOutDto) {
        return KakaoPayApprovalParams.builder()
            .cid("TC0ONETIME")
            .tid(paymentOutDto.getTid())
            .partner_order_id(paymentOutDto.getOrderId())
            .pg_token(kakaoPayApprovalINDto.getPgToken())
            .partner_user_id(kakaoPayApprovalINDto.getMemberId().toString())
            .build();
    }
}
