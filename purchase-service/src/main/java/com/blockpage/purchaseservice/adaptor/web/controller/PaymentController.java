package com.blockpage.purchaseservice.adaptor.web.controller;

import com.blockpage.purchaseservice.adaptor.web.apispec.KakaoPayApprovalRequest;
import com.blockpage.purchaseservice.adaptor.web.apispec.KakaoPayReadyRequest;
import com.blockpage.purchaseservice.adaptor.web.apispec.ApiWrapperResponse;
import com.blockpage.purchaseservice.adaptor.web.view.KakaoPayReadyView;
import com.blockpage.purchaseservice.adaptor.web.view.KakaoPaySuccessView;
import com.blockpage.purchaseservice.application.port.in.KakaoPayApprovalInDto;
import com.blockpage.purchaseservice.application.port.in.KakaoPayReadyInDto;
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
    private final Long TEST_MEMBER_ID = 3L;

    /***
     * 카카오페이 결제 준비 요청
     * @param kakaoPayReadyRequest
     */
    @PostMapping("/kakaopay/ready")
    public ResponseEntity<ApiWrapperResponse> postKakaoPayReady(@RequestBody KakaoPayReadyRequest kakaoPayReadyRequest) {

        KakaoPayReadyView kakaoPayReadyView = kakaoPayUseCase.kakaoPayReady(
            KakaoPayReadyInDto.toDto(TEST_MEMBER_ID, kakaoPayReadyRequest));
        return ResponseEntity.status(HttpStatus.OK)
            .body(new ApiWrapperResponse(kakaoPayReadyView));
    }

    /**
     * 카카오페이 결제 승인 요청
     *
     * @param kakaoPayApprovalRequest
     */
    @PostMapping("/kakaopay/approval")
    public ResponseEntity<ApiWrapperResponse> postKakaoPayReady(@RequestBody KakaoPayApprovalRequest kakaoPayApprovalRequest) {
        KakaoPaySuccessView kakaoPaySuccessView = kakaoPayUseCase.kakaoPayApproval(
            KakaoPayApprovalInDto.toDto(TEST_MEMBER_ID, kakaoPayApprovalRequest));
        return ResponseEntity.status(HttpStatus.OK)
            .body(new ApiWrapperResponse(kakaoPaySuccessView));
    }
}

