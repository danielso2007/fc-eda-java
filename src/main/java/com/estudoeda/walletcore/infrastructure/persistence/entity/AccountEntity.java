package com.estudoeda.walletcore.infrastructure.persistence.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "accounts")
@EntityListeners(AuditingEntityListener.class)
@SuppressWarnings("PMD.DataClass")
public class AccountEntity {

    @Id
    @Column(name = "id", nullable = false, updatable = false, columnDefinition = "UUID")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", nullable = false, updatable = false)
    private ClientEntity client;

    @Column(name = "client_id", nullable = false, updatable = false, insertable = false)
    private UUID clientId;

    @Column(name = "balance", nullable = false)
    private double balance;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @LastModifiedDate
    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    protected AccountEntity() {
    }

    public AccountEntity(final ClientEntity client, final double balance) {
        this.id = UUID.randomUUID();
        this.client = client;
        this.clientId = client.getId();
        this.balance = balance;
    }

    public static AccountEntity createAccount(final ClientEntity client) {
        if (Objects.isNull(client)) {
            return null;
        }
        AccountEntity account = new AccountEntity(client, 0.0);
        return account;
    }

    public UUID getId() {
        return id;
    }

    public ClientEntity getClient() {
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