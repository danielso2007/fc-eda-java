package com.estudoeda.walletcore.application.domain.model;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Client {

    private UUID id;

    private String name;

    private String email;

    private List<Account> accounts;

    private Instant createdAt;

    private Instant updatedAt;

    protected Client() {
    }

    private Client(String name, String email) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.email = email;
        this.accounts = new ArrayList<>();
        validate();
    }

    public static Client newClient(String name, String email) {
        return new Client(name, email);
    }

    private final void validate() {
        if (Objects.isNull(this.name) || this.name.isBlank()) {
            throw new IllegalArgumentException("name is required");
        }
        if (Objects.isNull(this.email) || this.email.isBlank()) {
            throw new IllegalArgumentException("email is required");
        }
    }

    public void update(String name, String email) {
        this.name = name;
        this.email = email;
        validate();
    }

    public void addAccount(Account account) {
        if (!account.getClientId().equals(this.id)) {
            throw new IllegalArgumentException("account does not belong to client");
        }
        this.accounts.add(account);
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public List<Account> getAccounts() {
        return Collections.unmodifiableList(accounts);
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }
}