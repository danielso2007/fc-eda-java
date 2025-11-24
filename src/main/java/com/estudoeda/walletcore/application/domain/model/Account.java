package com.estudoeda.walletcore.application.domain.model;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

public class Account {

    private UUID id;

    private Client client;

    private UUID clientId;

    private double balance;

    private Instant createdAt;

    private Instant updatedAt;

    protected Account() {
    }

    public static Account createAccount(Client client) {
        if (Objects.isNull(client)) {
            return null;
        }
        Account account = new Account();
        account.id = UUID.randomUUID();
        account.client = client;
        account.clientId = client.getId();
        account.balance = 0.0;
        return account;
    }

    public void credit(double amount) {
        this.balance += amount;
    }

    public void debit(double amount) {
        this.balance -= amount;
    }

    public UUID getId() {
        return id;
    }

    public Client getClient() {
        return client;
    }

    public UUID getClientId() {
        return clientId;
    }

    public double getBalance() {
        return balance;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }
}