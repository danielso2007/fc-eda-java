package com.estudoeda.walletcore.infrastructure.persistence.entity;

import java.time.Instant;
import java.util.UUID;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false, updatable = false, columnDefinition = "UUID")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_from_id", nullable = false, updatable = false, insertable = false)
    private AccountEntity accountFrom;

    @Column(name = "account_from_id", nullable = false)
    private UUID accountFromId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_to_id", nullable = false, updatable = false, insertable = false)
    private AccountEntity accountTo;

    @Column(name = "account_to_id", nullable = false)
    private UUID accountToId;

    @Column(name = "amount", nullable = false)
    private double amount;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    public TransactionEntity() {
    }

    private TransactionEntity(final AccountEntity accountFrom, final AccountEntity accountTo, final double amount) {
        this.id = UUID.randomUUID();
        this.accountFrom = accountFrom;
        this.accountFromId = accountFrom.getId();
        this.accountTo = accountTo;
        this.accountToId = accountTo.getId();
        this.amount = amount;
    }

    public static TransactionEntity newTransaction(final AccountEntity accountFrom, final AccountEntity accountTo,
            final double amount) {
        final TransactionEntity transaction = new TransactionEntity(accountFrom, accountTo, amount);
        return transaction;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public AccountEntity getAccountFrom() {
        return accountFrom;
    }

    public void setAccountFrom(AccountEntity accountFrom) {
        this.accountFrom = accountFrom;
    }

    public UUID getAccountFromId() {
        return accountFromId;
    }

    public void setAccountFromId(UUID accountFromId) {
        this.accountFromId = accountFromId;
    }

    public AccountEntity getAccountTo() {
        return accountTo;
    }

    public void setAccountTo(AccountEntity accountTo) {
        this.accountTo = accountTo;
    }

    public UUID getAccountToId() {
        return accountToId;
    }

    public void setAccountToId(UUID accountToId) {
        this.accountToId = accountToId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((accountFromId == null) ? 0 : accountFromId.hashCode());
        result = prime * result + ((accountToId == null) ? 0 : accountToId.hashCode());
        long temp;
        temp = Double.doubleToLongBits(amount);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        result = prime * result + ((createdAt == null) ? 0 : createdAt.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TransactionEntity other = (TransactionEntity) obj;
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
            return false;
        }
        if (accountFromId == null) {
            if (other.accountFromId != null) {
                return false;
            }
        } else if (!accountFromId.equals(other.accountFromId)) {
            return false;
        }
        if (accountToId == null) {
            if (other.accountToId != null) {
                return false;
            }
        } else if (!accountToId.equals(other.accountToId)) {
            return false;
        }
        if (Double.doubleToLongBits(amount) != Double.doubleToLongBits(other.amount)) {
            return false;
        }
        if (createdAt == null) {
            if (other.createdAt != null) {
                return false;
            }
        } else if (!createdAt.equals(other.createdAt)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "TransactionEntity [id=" + id + ", accountFromId=" + accountFromId + ", accountToId=" + accountToId
                + ", amount=" + amount + "]";
    }

}