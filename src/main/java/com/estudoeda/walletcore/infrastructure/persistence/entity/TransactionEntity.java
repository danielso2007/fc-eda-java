package com.estudoeda.walletcore.infrastructure.persistence.entity;

import java.time.Instant;
import java.util.UUID;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "transactions")
@EntityListeners(AuditingEntityListener.class)
@SuppressWarnings("PMD.DataClass")
public class TransactionEntity {

    @Id
    @Column(name = "id", nullable = false, updatable = false, columnDefinition = "UUID")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_from_id", nullable = false)
    private AccountEntity accountFrom;

    @Column(name = "account_from_id", nullable = false, insertable = false, updatable = false)
    private UUID accountFromId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_to_id", nullable = false)
    private AccountEntity accountTo;

    @Column(name = "account_to_id", nullable = false, insertable = false, updatable = false)
    private UUID accountToId;

    @Column(name = "amount", nullable = false)
    private double amount;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    protected TransactionEntity() {
    }

    private TransactionEntity(final AccountEntity accountFrom, final AccountEntity accountTo, final double amount) {
        this.id = UUID.randomUUID();
        this.accountFrom = accountFrom;
        this.accountFromId = accountFrom.getId();
        this.accountTo = accountTo;
        this.accountToId = accountTo.getId();
        this.amount = amount;
    }

    public static TransactionEntity newTransaction(final AccountEntity accountFrom, final AccountEntity accountTo, final double amount) {
        TransactionEntity transaction = new TransactionEntity(accountFrom, accountTo, amount);
        return transaction;
    }

    public UUID getId() {
        return id;
    }

    public AccountEntity getAccountFrom() {
        return accountFrom;
    }

    public UUID getAccountFromId() {
        return accountFromId;
    }

    public AccountEntity getAccountTo() {
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