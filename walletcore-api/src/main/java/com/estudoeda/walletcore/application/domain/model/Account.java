package com.estudoeda.walletcore.application.domain.model;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

public class Account {

    private UUID id;

    private Client client;

    private UUID clientId;

    private BigDecimal balance;

    private Instant createdAt;

    private Instant updatedAt;

    protected Account() {
    }

    public Account(UUID id) {
        this.id = id;
    }

    public static Account createAccount(Client client) {
        if (Objects.isNull(client)) {
            return null;
        }
        Account account = new Account();
        account.client = client;
        account.clientId = client.getId();
        account.balance = BigDecimal.ZERO;
        return account;
    }

    public static Account createAccount(UUID clientId) {
        if (Objects.isNull(clientId)) {
            return null;
        }
        Account account = new Account();
        account.clientId = clientId;
        account.balance = BigDecimal.ZERO;
        return account;
    }

    public void credit(BigDecimal amount) {
        this.balance = this.balance.add(amount);
    }

    public void debit(BigDecimal amount) {
        this.balance = this.balance.subtract(amount);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public UUID getClientId() {
        return clientId;
    }

    public void setClientId(UUID clientId) {
        this.clientId = clientId;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

}