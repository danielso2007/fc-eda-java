package com.estudoeda.walletcore.application.port.out;

import java.util.concurrent.CompletableFuture;
import com.estudoeda.walletcore.application.domain.event.WalletEvent;

public interface EventPublisher {
    CompletableFuture<Void> publish(WalletEvent event);
}
