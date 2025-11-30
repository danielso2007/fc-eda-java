package com.estudoeda.walletcore.application.domain.event;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public class BalanceUpdatedWalletEvent {

    private UUID id;
    private UUID accountId;
    private TypeBalanceEnum entryType;
    private BigDecimal amount;
    private UUID transactionId;
    private Instant transactionAt;

    private BalanceUpdatedWalletEvent() {
    }

    private BalanceUpdatedWalletEvent(final UUID accountId, final TypeBalanceEnum entryType, final BigDecimal amount,
            final UUID transactionId,
            final Instant transactionAt) {
        this.id = UUID.randomUUID();
        this.accountId = accountId;
        this.entryType = entryType;
        this.amount = amount;
        this.transactionId = transactionId;
        this.transactionAt = transactionAt;
    }

    public static BalanceUpdatedWalletEvent create(final UUID accountId, final TypeBalanceEnum typeBalance,
            final BigDecimal amount,
            final UUID transactionId, final Instant transactionAt) {
        return new BalanceUpdatedWalletEvent(accountId, typeBalance, ajustarValorPeloTipoDeBalanco(typeBalance, amount),
                transactionId, transactionAt);
    }

    private static BigDecimal ajustarValorPeloTipoDeBalanco(final TypeBalanceEnum entryType, final BigDecimal amount) {
        if (TypeBalanceEnum.DEBIT.equals(entryType)) {
            return amount.negate();
        }
        return amount;
    }

    public UUID getId() {
        return id;
    }

    public void setId(final UUID id) {
        this.id = id;
    }

    public UUID getAccountId() {
        return accountId;
    }

    public void setAccountId(final UUID accountId) {
        this.accountId = accountId;
    }

    public TypeBalanceEnum getEntryType() {
        return entryType;
    }

    public void setEntryType(final TypeBalanceEnum entryType) {
        this.entryType = entryType;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(final BigDecimal amount) {
        this.amount = amount;
    }

    public UUID getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(final UUID transactionId) {
        this.transactionId = transactionId;
    }

    public Instant getTransactionAt() {
        return transactionAt;
    }

    public void setTransactionAt(final Instant transactionAt) {
        this.transactionAt = transactionAt;
    }

}
