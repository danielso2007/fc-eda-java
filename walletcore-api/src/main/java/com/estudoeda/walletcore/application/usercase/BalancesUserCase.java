package com.estudoeda.walletcore.application.usercase;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public interface BalancesUserCase {

    void sendBalanceDebit(UUID accountId, BigDecimal amount, UUID transactionId, Instant transactionAt);

    void sendBalanceCredit(UUID accountId, BigDecimal amount, UUID transactionId, Instant transactionAt);

}