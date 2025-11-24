package com.estudoeda.walletcore.infrastructure.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import com.estudoeda.walletcore.application.domain.event.WalletEvent;
import com.estudoeda.walletcore.application.usercase.WalletUserCase;

@Component
public class WalletEventConsumer {

    private final WalletUserCase service;

    public WalletEventConsumer(WalletUserCase service) {
        this.service = service;
    }

    @KafkaListener(topics = "${kafka.topic}", groupId = "${kafka.group.id.config}", containerFactory = "kafkaListenerContainerFactory")
    public void onMessage(WalletEvent ev) {
        service.createWallet(ev.payload());
    }
}
