package com.estudoeda.walletcore.application.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "transactions")
@EntityListeners(AuditingEntityListener.class)
@SuppressWarnings("PMD.DataClass")
public class Transaction {

    @Id
    @Column(name = "id", nullable = false, updatable = false, columnDefinition = "UUID")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_from_id", nullable = false)
    private Account accountFrom;

    @Column(name = "account_from_id", nullable = false, insertable = false, updatable = false)
    private UUID accountFromId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_to_id", nullable = false)
    private Account accountTo;

    @Column(name = "account_to_id", nullable = false, insertable = false, updatable = false)
    private UUID accountToId;

    @Column(name = "amount", nullable = false)
    private double amount;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
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