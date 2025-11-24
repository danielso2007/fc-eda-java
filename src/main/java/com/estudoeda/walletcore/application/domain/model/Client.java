package com.estudoeda.walletcore.application.domain.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "clients")
@EntityListeners(AuditingEntityListener.class)
public class Client {

    @Id
    @Column(name = "id", nullable = false, updatable = false, columnDefinition = "UUID")
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Account> accounts;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @LastModifiedDate
    @Column(name = "updated_at", nullable = false)
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