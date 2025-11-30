package com.estudoeda.walletcore.application.port.out;

import java.util.concurrent.CompletableFuture;
import com.estudoeda.walletcore.application.domain.event.BalanceUpdatedWalletEvent;

public interface EventPublisherBalance {
    CompletableFuture<Void> publish(BalanceUpdatedWalletEvent event);
}
