package com.blockpage.purchaseservice.adaptor.web.controller;

import com.blockpage.purchaseservice.adaptor.web.apispec.KakaoPayReadyRequest;
import com.blockpage.purchaseservice.adaptor.web.apispec.ApiWrapperResponse;
import com.blockpage.purchaseservice.adaptor.web.view.KakaoPayReadyView;
import com.blockpage.purchaseservice.application.port.in.KakaoPayInPortDto;
import com.blockpage.purchaseservice.application.port.in.KakaoPayUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/payments")
public class PaymentController {

    private final KakaoPayUseCase kakaoPayUseCase;

    @PostMapping("/kakaopay/ready")
    public ResponseEntity<ApiWrapperResponse> postKakaoPayReady(@RequestBody KakaoPayReadyRequest kakaoPayReadyRequest) {
        Long memberId = 1L;

        KakaoPayReadyView kakaoPayReadyView = kakaoPayUseCase.kakaoPayReady(KakaoPayInPortDto.toInPortDto(memberId, kakaoPayReadyRequest));
        return ResponseEntity.status(HttpStatus.OK)
            .body(new ApiWrapperResponse(kakaoPayReadyView));
    }
}

