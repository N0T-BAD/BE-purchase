package com.blockpage.purchaseservice.application.port.out;

import com.blockpage.purchaseservice.adaptor.external.kakao.apispec.KakaoPayApprovalParams;
import com.blockpage.purchaseservice.adaptor.external.kakao.apispec.KakaoPayApprovalResponse;

public interface KakaoPayApprovalPort {

    KakaoPayApprovalResponse approval(KakaoPayApprovalParams kakaoPayApprovalParams);
}
