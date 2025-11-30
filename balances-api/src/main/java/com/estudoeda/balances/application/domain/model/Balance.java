package com.estudoeda.balances.application.domain.model;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

public class Balance {

    private UUID id;
    private UUID accountId;
    private TypeBalanceEnum entryType;
    private BigDecimal amount;
    private UUID transactionId;
    private Instant transactionAt;
    private Instant createdAt;

    public Balance() {
    }

    public Balance(UUID id) {
        this.id = id;
    }

    public Balance(UUID accountId,
            TypeBalanceEnum entryType,
            BigDecimal amount,
            UUID transactionId,
            Instant transactionAt) {
        this.accountId = accountId;
        this.entryType = entryType;
        this.amount = amount;
        this.transactionId = transactionId;
        this.transactionAt = transactionAt;
    }

    private Balance(Builder builder) {
        this.id = builder.id;
        this.accountId = builder.accountId;
        this.entryType = builder.entryType;
        this.amount = builder.amount;
        this.transactionId = builder.transactionId;
        this.transactionAt = builder.transactionAt;
        validate();
    }

    private void validate() {
        Objects.requireNonNull(id, "Id cannot be null");
        Objects.requireNonNull(accountId, "AccountId cannot be null");
        Objects.requireNonNull(entryType, "EntryType cannot be null");
        Objects.requireNonNull(transactionId, "TransactionId cannot be null");
        Objects.requireNonNull(transactionAt, "TransactionAt cannot be null");

        if (Objects.isNull(amount) || amount.signum() <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
    }

    public static class Builder {

        private UUID id;
        private UUID accountId;
        private TypeBalanceEnum entryType;
        private BigDecimal amount;
        private UUID transactionId;
        private Instant transactionAt;

        public Builder id(UUID id) {
            this.id = id;
            return this;
        }

        public Builder accountId(UUID accountId) {
            this.accountId = accountId;
            return this;
        }

        public Builder entryType(TypeBalanceEnum entryType) {
            this.entryType = entryType;
            return this;
        }

        public Builder amount(BigDecimal amount) {
            this.amount = amount;
            return this;
        }

        public Builder transactionId(UUID transactionId) {
            this.transactionId = transactionId;
            return this;
        }

        public Builder transactionAt(Instant transactionAt) {
            this.transactionAt = transactionAt;
            return this;
        }

        public Balance build() {
            return new Balance(this);
        }
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
        result = prime * result + ((accountId == null) ? 0 : accountId.hashCode());
        result = prime * result + ((entryType == null) ? 0 : entryType.hashCode());
        result = prime * result + ((amount == null) ? 0 : amount.hashCode());
        result = prime * result + ((transactionId == null) ? 0 : transactionId.hashCode());
        result = prime * result + ((transactionAt == null) ? 0 : transactionAt.hashCode());

        return result;
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        if (getClass() != obj.getClass()) {
            return false;
        }

        Balance other = (Balance) obj;

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
            return other.transactionAt == null;
        } else {
            return transactionAt.equals(other.transactionAt);
        }
    }

    @Override
    public String toString() {
        return "Balance { "
            + "id=" + id
            + ", accountId=" + accountId
            + ", entryType=" + entryType
            + ", amount=" + amount
            + ", transactionId=" + transactionId
            + ", transactionAt=" + transactionAt
            + " }";
    }

}
