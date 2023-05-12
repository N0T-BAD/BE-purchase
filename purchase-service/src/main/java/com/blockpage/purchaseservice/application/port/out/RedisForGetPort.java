package com.blockpage.purchaseservice.application.port.out;

public interface RedisForGetPort {

    PaymentOutDto getPaymentReceiptByMemberId(String memberId);

}
