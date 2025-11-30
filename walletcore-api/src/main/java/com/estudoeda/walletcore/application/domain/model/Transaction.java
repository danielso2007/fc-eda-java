package com.estudoeda.walletcore.application.domain.model;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;
import com.estudoeda.walletcore.application.exception.AmountException;
import com.estudoeda.walletcore.application.exception.InsufficientFundsException;

@SuppressWarnings("PMD.DataClass")
public class Transaction {

    private UUID id;

    private Account accountFrom;

    private UUID accountFromId;

    private Account accountTo;

    private UUID accountToId;

    private BigDecimal amount;

    private Instant createdAt;

    protected Transaction() {
    }

    public Transaction(UUID id) {
        this.id = id;
    }

    private Transaction(Account accountFrom, Account accountTo, BigDecimal amount) {
        this.accountFrom = accountFrom;
        this.accountFromId = accountFrom.getId();
        this.accountTo = accountTo;
        this.accountToId = accountTo.getId();
        this.amount = amount;
        validate();
    }

    public static Transaction newTransaction(Account accountFrom, Account accountTo, BigDecimal amount) {
        Transaction transaction = new Transaction(accountFrom, accountTo, amount);
        transaction.commit();
        return transaction;
    }

    public void commit() {
        this.accountFrom.debit(this.amount);
        this.accountTo.credit(this.amount);
    }

    public final void validate() {
        if (this.amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new AmountException("O valor deve ser maior que zero.");
        }
        if (this.accountFrom.getBalance().compareTo(this.amount) < 0) {
            throw new InsufficientFundsException("Fundos insuficientes.");
        }
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Account getAccountFrom() {
        return accountFrom;
    }

    public void setAccountFrom(Account accountFrom) {
        this.accountFrom = accountFrom;
    }

    public UUID getAccountFromId() {
        return accountFromId;
    }

    public void setAccountFromId(UUID accountFromId) {
        this.accountFromId = accountFromId;
    }

    public Account getAccountTo() {
        return accountTo;
    }

    public void setAccountTo(Account accountTo) {
        this.accountTo = accountTo;
    }

    public UUID getAccountToId() {
        return accountToId;
    }

    public void setAccountToId(UUID accountToId) {
        this.accountToId = accountToId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

}