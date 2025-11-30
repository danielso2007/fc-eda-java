package com.estudoeda.balances.application.domain.event;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;
import com.estudoeda.balances.application.domain.model.Balance;
import com.estudoeda.balances.application.domain.model.TypeBalanceEnum;

public class BalanceUpdatedWalletEvent {

    private UUID id;
    private UUID accountId;
    private TypeBalanceEnum entryType;
    private BigDecimal amount;
    private UUID transactionId;
    private Instant transactionAt;

    private BalanceUpdatedWalletEvent() {
    }

    private BalanceUpdatedWalletEvent(UUID accountId, TypeBalanceEnum entryType, BigDecimal amount, UUID transactionId,
            Instant transactionAt) {
        this.id = UUID.randomUUID();
        this.accountId = accountId;
        this.entryType = entryType;
        this.amount = amount;
        this.transactionId = transactionId;
        this.transactionAt = transactionAt;
    }

    public static BalanceUpdatedWalletEvent create(UUID accountId, TypeBalanceEnum entryType, BigDecimal amount,
            UUID transactionId, Instant transactionAt) {
        return new BalanceUpdatedWalletEvent(accountId, entryType, amount, transactionId, transactionAt);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getAccountId() {
        return accountId;
    }

    public void setAccountId(UUID accountId) {
        this.accountId = accountId;
    }

    public TypeBalanceEnum getEntryType() {
        return entryType;
    }

    public void setEntryType(TypeBalanceEnum entryType) {
        this.entryType = entryType;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public UUID getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(UUID transactionId) {
        this.transactionId = transactionId;
    }

    public Instant getTransactionAt() {
        return transactionAt;
    }

    public void setTransactionAt(Instant transactionAt) {
        this.transactionAt = transactionAt;
    }

    public Balance toModel() {
        return new Balance(this.accountId, this.entryType, this.amount, this.transactionId, this.transactionAt);
    }

}
