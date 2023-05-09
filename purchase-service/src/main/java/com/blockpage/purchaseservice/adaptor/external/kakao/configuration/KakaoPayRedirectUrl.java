package com.blockpage.purchaseservice.adaptor.external.kakao.configuration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum KakaoPayRedirectUrl {

    KAKAO_APPROVAL_URL("https://localhost:3000/v1/purchases/approval","결제 승인"),
    KAKAO_CANCEL_URL("https://localhost:3000/v1/purchases/cancel","결제 취소"),
    KAKAO_FAIL_URL("https://localhost:3000/v1/purchases/fail","결제 실패"),
    ;
    private String url;
    private String value;
}
