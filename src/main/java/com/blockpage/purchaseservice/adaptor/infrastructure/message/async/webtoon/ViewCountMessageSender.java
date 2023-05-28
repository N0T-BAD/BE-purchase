package com.blockpage.purchaseservice.adaptor.infrastructure.message.async.webtoon;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Component
@RequiredArgsConstructor
public class ViewCountMessageSender {

    @Value("${spring.kafka.topicName}")
    private String topicName;

    private final KafkaTemplate kafkaTemplate;

    public void sendViewCount(ViewCountMessage viewCountMessage) {
        Message<ViewCountMessage> message = MessageBuilder.withPayload(viewCountMessage)
            .setHeader(KafkaHeaders.TOPIC, topicName)
            .build();

        ListenableFuture<SendResult<String, ViewCountMessage>> future = kafkaTemplate.send(message);

        future.addCallback(new ListenableFutureCallback<SendResult<String, ViewCountMessage>>() {
            @Override
            public void onSuccess(SendResult<String, ViewCountMessage> result) {
                System.out.println(
                    "Send message=[" + result.getProducerRecord().value().toString() + "] with offset=[" + result.getRecordMetadata()
                        .offset() + "]");
            }

            @Override
            public void onFailure(Throwable ex) {
                System.out.println("Unable to send message due to = " + ex.getMessage());
            }
        });
    }
}
