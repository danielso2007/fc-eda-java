package com.estudoeda.walletcore.infrastructure.service;

import java.time.Instant;
import java.util.UUID;
import org.springframework.stereotype.Service;
import com.estudoeda.walletcore.application.domain.event.WalletEvent;
import com.estudoeda.walletcore.application.port.out.EventPublisher;
import com.estudoeda.walletcore.application.usercase.WalletUserCase;

@Service
public class WalletService implements WalletUserCase {
    private final EventPublisher publisher;

    public WalletService(EventPublisher publisher) {
        this.publisher = publisher;
    }

    @Override
    public void createWallet(String payload) {
        WalletEvent ev = new WalletEvent(UUID.randomUUID(), WalletEvent.TYPE_CREATED_EVENT, payload, Instant.now());
        publisher.publish(ev).join();
    }
}
