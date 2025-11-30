package com.estudoeda.walletcore.application.domain.model;

import java.time.Instant;
import java.util.ArrayList;
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

    public Client(UUID id) {
        this.id = id;
    }

    private Client(String name, String email) {
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
            throw new IllegalArgumentException("Name is required");
        }
        if (Objects.isNull(this.email) || this.email.isBlank()) {
            throw new IllegalArgumentException("Email is required");
        }
    }

    public void update(String name, String email) {
        this.name = name;
        this.email = email;
        validate();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
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