package com.blockpage.purchaseservice.application.port.out;

public interface RedisForSavePort {

    void savePaymentReceipt(PaymentOutDto paymentOutDto);

}
