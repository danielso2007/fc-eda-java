package com.estudoeda.walletcore.infrastructure.service;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;
import org.springframework.stereotype.Service;
import com.estudoeda.walletcore.application.domain.event.BalanceUpdatedWalletEvent;
import com.estudoeda.walletcore.application.domain.event.TypeBalanceEnum;
import com.estudoeda.walletcore.application.port.out.EventPublisherBalance;
import com.estudoeda.walletcore.application.usercase.BalancesUserCase;

@Service
public class BalancesService implements BalancesUserCase {
    private final EventPublisherBalance publisher;

    public BalancesService(final EventPublisherBalance publisher) {
        this.publisher = publisher;
    }

    @Override
    public void sendBalanceDebit(UUID accountId, BigDecimal amount, UUID transactionId, Instant transactionAt) {
        final BalanceUpdatedWalletEvent ev = BalanceUpdatedWalletEvent.create(accountId, TypeBalanceEnum.DEBIT, amount,
                transactionId,
                transactionAt);
        publisher.publish(ev).join();
    }

    @Override
    public void sendBalanceCredit(UUID accountId, BigDecimal amount, UUID transactionId, Instant transactionAt) {
        final BalanceUpdatedWalletEvent ev = BalanceUpdatedWalletEvent.create(accountId, TypeBalanceEnum.CREDIT, amount,
                transactionId, transactionAt);
        publisher.publish(ev).join();
    }
}
