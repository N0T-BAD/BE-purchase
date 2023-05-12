package com.blockpage.purchaseservice.adaptor.infrastructure.redis;

import com.blockpage.purchaseservice.application.port.out.PaymentOutDto;
import com.blockpage.purchaseservice.application.port.out.RedisForGetPort;
import com.blockpage.purchaseservice.application.port.out.RedisForSavePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RedisPersistenceAdaptor implements RedisForSavePort, RedisForGetPort {

    private final RedisRepository redisRepository;

    @Override
    public void savePaymentReceipt(PaymentOutDto paymentOutDto) {
        redisRepository.saveHashReceipt(paymentOutDto);
    }

    @Override
    public PaymentOutDto getPaymentReceiptByMemberId(String memberId) {
        return redisRepository.getHashReceiptByMemberId(memberId);
    }
}
