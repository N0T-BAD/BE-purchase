package com.blockpage.purchaseservice.application.port.in;

import com.blockpage.purchaseservice.adaptor.web.view.KakaoPayReadyView;

public interface KakaoPayUseCase {

    KakaoPayReadyView kakaoPayReady(KakaoPayInPortDto kakaoPayInPortDto);
}
