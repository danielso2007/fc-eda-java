package com.estudoeda.balances.infrastructure.persistence.entity;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import com.estudoeda.balances.application.domain.model.TypeBalanceEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "balances")
@EntityListeners(AuditingEntityListener.class)
public class BalanceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false, columnDefinition = "uuid")
    private UUID id;

    @Column(name = "account_id", nullable = false, columnDefinition = "uuid")
    private UUID accountId;

    @Enumerated(EnumType.STRING)
    @Column(name = "entry_type", nullable = false, length = 32)
    private TypeBalanceEnum entryType;

    @Column(name = "amount", nullable = false, precision = 19, scale = 4)
    private BigDecimal amount;

    @Column(name = "transaction_id", nullable = false, columnDefinition = "uuid")
    private UUID transactionId;

    @Column(name = "transaction_at", nullable = false)
    private Instant transactionAt;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false, nullable = false)
    private Instant createdAt;

    public BalanceEntity() {
    }

    public BalanceEntity(final UUID id) {
        this.id = id;
    }

    public BalanceEntity(
            final UUID accountId,
            final TypeBalanceEnum entryType,
            final BigDecimal amount,
            final UUID transactionId,
            final Instant transactionAt) {
        this.accountId = accountId;
        this.entryType = entryType;
        this.amount = amount;
        this.transactionId = transactionId;
        this.transactionAt = transactionAt;
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

    public Instant getCreatedAt() {
        return createdAt;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((accountId == null) ? 0 : accountId.hashCode());
        result = prime * result + ((entryType == null) ? 0 : entryType.hashCode());
        result = prime * result + ((amount == null) ? 0 : amount.hashCode());
        result = prime * result + ((transactionId == null) ? 0 : transactionId.hashCode());
        result = prime * result + ((transactionAt == null) ? 0 : transactionAt.hashCode());
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
        final BalanceEntity other = (BalanceEntity) obj;
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
            return false;
        }
        if (accountId == null) {
            if (other.accountId != null) {
                return false;
            }
        } else if (!accountId.equals(other.accountId)) {
            return false;
        }
        if (entryType != other.entryType) {
            return false;
        }
        if (amount == null) {
            if (other.amount != null) {
                return false;
            }
        } else if (!amount.equals(other.amount)) {
            return false;
        }
        if (transactionId == null) {
            if (other.transactionId != null) {
                return false;
            }
        } else if (!transactionId.equals(other.transactionId)) {
            return false;
        }
        if (transactionAt == null) {
            if (other.transactionAt != null) {
                return false;
            }
        } else if (!transactionAt.equals(other.transactionAt)) {
            return false;
        }
        if (createdAt == null) {
            return other.createdAt == null;
        } else {
            return createdAt.equals(other.createdAt);
        }
    }

    @Override
    public String toString() {
        return "BalanceEntity [id=" + id + ", accountId=" + accountId + ", entryType=" + entryType + ", amount="
                + amount + ", transactionId=" + transactionId + ", transactionAt=" + transactionAt + ", createdAt="
                + createdAt + "]";
    }

}
