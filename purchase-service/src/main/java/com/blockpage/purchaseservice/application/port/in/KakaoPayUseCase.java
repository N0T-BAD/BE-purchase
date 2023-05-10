package com.blockpage.purchaseservice.application.port.in;

import com.blockpage.purchaseservice.adaptor.web.view.KakaoPayReadyView;
import com.blockpage.purchaseservice.adaptor.web.view.KakaoPaySuccessView;

public interface KakaoPayUseCase {

    KakaoPayReadyView kakaoPayReady(KakaoPayReadyInDto kakaoPayReadyInDto);

    KakaoPaySuccessView kakaoPayApproval(KakaoPayApprovalInDto kakaoPayApprovalINDto);
}
