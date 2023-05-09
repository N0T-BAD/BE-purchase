package com.blockpage.purchaseservice.adaptor.external.kakao.apispec;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class KakaoPayReadyRequest {

    private String itemName;
    private Integer quantity;
    private Integer totalAmount;
}
