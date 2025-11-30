package com.estudoeda.walletcore.infrastructure.kafka;

import java.util.concurrent.CompletableFuture;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import com.estudoeda.walletcore.application.domain.event.BalanceUpdatedWalletEvent;
import com.estudoeda.walletcore.application.port.out.EventPublisherBalance;

@Component
public class KafkaEventProducer implements EventPublisherBalance {

    private final KafkaTemplate<String, Object> kafkaTemplate;
    @Value("${kafka.topic}")
    private String topic;

    public KafkaEventProducer(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public CompletableFuture<Void> publish(BalanceUpdatedWalletEvent event) {
        String key = event.getId().toString();
        return kafkaTemplate.send(topic, key, event).thenApply(r -> null);
    }
}