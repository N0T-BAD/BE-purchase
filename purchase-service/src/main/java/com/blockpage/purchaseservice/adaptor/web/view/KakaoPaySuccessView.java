package com.blockpage.purchaseservice.adaptor.web.view;

import com.blockpage.purchaseservice.adaptor.external.kakao.apispec.Amount;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class KakaoPaySuccessView {

    private String userId;
    private String orderId;

    private String paymentMethodType;
    private int amount;

    private String itemName;
    private int quantity;

    private String createdAt;
    private String approvedAt;

    public KakaoPaySuccessView(String userId, String orderId, String paymentMethodType, Amount amount, String itemName, int quantity,
        String createdAt, String approvedAt) {
        this.userId = userId;
        this.orderId = orderId;
        this.paymentMethodType = paymentMethodType;
        this.amount = amount.getTotal();
        this.itemName = itemName;
        this.quantity = quantity;
        this.createdAt = createdAt;
        this.approvedAt = approvedAt;
    }
}
