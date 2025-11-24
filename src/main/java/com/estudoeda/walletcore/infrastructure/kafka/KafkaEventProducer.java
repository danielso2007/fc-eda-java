package com.estudoeda.walletcore.infrastructure.kafka;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import com.estudoeda.walletcore.application.domain.event.WalletEvent;
import com.estudoeda.walletcore.application.port.out.EventPublisher;
import java.util.concurrent.CompletableFuture;

@Component
public class KafkaEventProducer implements EventPublisher {

    private final KafkaTemplate<String, Object> kafkaTemplate;
    @Value("${kafka.topic}")
    private String topic;

    public KafkaEventProducer(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public CompletableFuture<Void> publish(WalletEvent event) {
        String key = event.id().toString();
        return kafkaTemplate.send(topic, key, event).thenApply(r -> null);
    }
}