package com.blockpage.purchaseservice.application.service;

import com.blockpage.purchaseservice.adaptor.external.kakao.apispec.KakaoPayApprovalParams;
import com.blockpage.purchaseservice.adaptor.external.kakao.apispec.KakaoPayApprovalResponse;
import com.blockpage.purchaseservice.adaptor.external.kakao.apispec.KakaoPayReadyParams;
import com.blockpage.purchaseservice.adaptor.external.kakao.apispec.KakaoPayReadyResponse;
import com.blockpage.purchaseservice.adaptor.web.view.KakaoPayReadyView;
import com.blockpage.purchaseservice.adaptor.web.view.KakaoPaySuccessView;
import com.blockpage.purchaseservice.application.port.in.KakaoPayApprovalInDto;
import com.blockpage.purchaseservice.application.port.in.KakaoPayReadyInDto;
import com.blockpage.purchaseservice.application.port.in.KakaoPayUseCase;
import com.blockpage.purchaseservice.application.port.out.KakaoPayApprovalPort;
import com.blockpage.purchaseservice.application.port.out.KakaoPayReadyPort;
import com.blockpage.purchaseservice.application.port.out.PaymentOutDto;
import com.blockpage.purchaseservice.application.port.out.RedisForGetPort;
import com.blockpage.purchaseservice.application.port.out.RedisForSavePort;
import com.blockpage.purchaseservice.domain.Payment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class PaymentService implements KakaoPayUseCase {

    private final KakaoPayReadyPort kakaoPayReadyPort;
    private final KakaoPayApprovalPort kakaoPayApprovalPort;
    private final RedisForSavePort redisForSavePort;
    private final RedisForGetPort redisForGetPort;

    @Override
    public KakaoPayReadyView kakaoPayReady(KakaoPayReadyInDto kakaoPayReadyInDto) {
        Long memberId = kakaoPayReadyInDto.getMemberId();
        Payment payment = new Payment(memberId);
        payment.createOrderNumber();

        KakaoPayReadyParams kakaoPayReadyParams = KakaoPayReadyParams.addEssentialParams(memberId, payment.getOrderNumber(),
            kakaoPayReadyInDto);
        KakaoPayReadyResponse response = kakaoPayReadyPort.ready(kakaoPayReadyParams);
        redisForSavePort.savePaymentReceipt(PaymentOutDto.toDto(response.getTid(), memberId, payment.getOrderNumber(), kakaoPayReadyInDto));
        return new KakaoPayReadyView(response.getTid(), response.getNext_redirect_pc_url());
    }

    @Override
    public KakaoPaySuccessView kakaoPayApproval(KakaoPayApprovalInDto kakaoPayApprovalINDto) {
        PaymentOutDto paymentOutDto = redisForGetPort.getPaymentReceiptByMemberId(kakaoPayApprovalINDto.getMemberId().toString());
        KakaoPayApprovalParams kakaoPayApprovalParams = KakaoPayApprovalParams.addEssentialParams(kakaoPayApprovalINDto, paymentOutDto);
        KakaoPayApprovalResponse response = kakaoPayApprovalPort.approval(kakaoPayApprovalParams);

        //결제 내역 저장

        return new KakaoPaySuccessView(response.getPartner_user_id(),
            response.getPartner_order_id(),
            response.getPayment_method_type(),
            response.getAmount(),
            response.getItem_name(),
            response.getQuantity(),
            response.getCreated_at(),
            response.getApproved_at()
        );
    }
}
