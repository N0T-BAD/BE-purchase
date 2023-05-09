package com.blockpage.purchaseservice.adaptor.web.controller;

import com.blockpage.purchaseservice.adaptor.external.kakao.apispec.KakaoPayReadyRequest;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/payments")
public class KakaoController {

    private String KAKAO_PAY = "kakaopay";

    private final KakaoPayUseCase kakaoPayUseCase;

    @PostMapping
    public ResponseEntity<ApiWrapperResponse> postPayment(
        @RequestParam String type,
        @RequestBody KakaoPayReadyRequest kakaoPayReadyRequest) {
        Long memberId = 1L;
        if (type.equals(KAKAO_PAY)) {
            KakaoPayReadyView kakaoPayReadyView = kakaoPayUseCase.kakaoPayReady(
                KakaoPayInPortDto.toInPortDto(memberId, kakaoPayReadyRequest));
            return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiWrapperResponse(kakaoPayReadyView));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ApiWrapperResponse("잘못된 요청 입니다."));
        }
    }
}
