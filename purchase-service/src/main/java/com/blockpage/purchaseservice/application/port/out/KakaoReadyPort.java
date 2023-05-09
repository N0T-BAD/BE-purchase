package com.blockpage.purchaseservice.application.port.out;

import com.blockpage.purchaseservice.adaptor.external.kakao.apispec.KakaoPayReadyParams;
import com.blockpage.purchaseservice.adaptor.external.kakao.apispec.KakaoPayReadyResponse;

public interface KakaoReadyPort {

    KakaoPayReadyResponse ready(KakaoPayReadyParams kakaoPayReadyParams);
}