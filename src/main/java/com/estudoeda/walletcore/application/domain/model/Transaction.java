package com.estudoeda.walletcore.application.domain.model;

import java.time.Instant;
import java.util.UUID;

@SuppressWarnings("PMD.DataClass")
public class Transaction {

    private UUID id;

    private Account accountFrom;

    private UUID accountFromId;

    private Account accountTo;

    private UUID accountToId;

    private double amount;

    private Instant createdAt;

    protected Transaction() {
    }

    private Transaction(Account accountFrom, Account accountTo, double amount) {
        this.id = UUID.randomUUID();
        this.accountFrom = accountFrom;
        this.accountFromId = accountFrom.getId();
        this.accountTo = accountTo;
        this.accountToId = accountTo.getId();
        this.amount = amount;
        validate();
    }

    public static Transaction newTransaction(Account accountFrom, Account accountTo, double amount) {
        Transaction transaction = new Transaction(accountFrom, accountTo, amount);
        transaction.commit();
        return transaction;
    }

    public void commit() {
        this.accountFrom.debit(this.amount);
        this.accountTo.credit(this.amount);
    }

    public final void validate() {
        if (this.amount <= 0) {
            throw new IllegalArgumentException("amount must be greater than zero");
        }
        if (this.accountFrom.getBalance() < this.amount) {
            throw new IllegalArgumentException("insufficient funds");
        }
    }

    public UUID getId() {
        return id;
    }

    public Account getAccountFrom() {
        return accountFrom;
    }

    public UUID getAccountFromId() {
        return accountFromId;
    }

    public Account getAccountTo() {
        return accountTo;
    }

    public UUID getAccountToId() {
        return accountToId;
    }

    public double getAmount() {
        return amount;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }
}