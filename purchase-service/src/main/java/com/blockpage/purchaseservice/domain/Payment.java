package com.blockpage.purchaseservice.domain;

import java.time.LocalDate;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.Getter;

@Getter
public class Payment {

    private Long memberId;
    private String orderNumber;

    public Payment(Long memberId) {
        this.memberId = memberId;
    }

    public void createOrderNumber() {
        Random random = new Random();
        random.setSeed(System.currentTimeMillis());

        String yyyyMmDd = LocalDate.now().toString().replace("-", "");
        String randomNumbers = Stream.generate(() -> random.nextInt(1, 10))
            .limit(6)
            .map(String::valueOf)
            .collect(Collectors.joining());
        this.orderNumber = yyyyMmDd + memberId + randomNumbers;
    }
}
