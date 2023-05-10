package com.blockpage.purchaseservice.application.port.out;

import com.blockpage.purchaseservice.application.port.in.KakaoPayReadyInDto;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PaymentOutDto {

    private String tid;
    private String memberId;
    private String orderId;

    private String itemName;
    private String quantity;
    private String totalAmount;

    public static PaymentOutDto toDto(String tid, Long memberId, String orderNumber,
        KakaoPayReadyInDto kakaoPayReadyInDto) {
        return PaymentOutDto.builder()
            .tid(tid)
            .memberId(memberId.toString())
            .orderId(orderNumber)
            .itemName(kakaoPayReadyInDto.getItemName())
            .quantity(kakaoPayReadyInDto.getQuantity().toString())
            .totalAmount(kakaoPayReadyInDto.getTotalAmount().toString())
            .build();
    }

    public static PaymentOutDto fromComponentToDto(String memberId, String tid, String orderNumber, String itemName, String quantity,
        String totalAmount) {
        return PaymentOutDto.builder()
            .memberId(memberId)
            .tid(tid)
            .orderId(orderNumber)
            .itemName(itemName)
            .quantity(quantity)
            .totalAmount(totalAmount)
            .build();
    }
}
