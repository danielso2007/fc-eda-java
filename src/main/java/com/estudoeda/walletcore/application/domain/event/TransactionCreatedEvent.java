package com.estudoeda.walletcore.application.domain.event;

import java.time.Instant;
import java.util.UUID;

public record TransactionCreatedEvent(
        UUID id,
        String name,
        String payload,
        Instant occurredAt) {
    public static final String TYPE_TRANSACTION_CREATE = "TRANSACTION_CREATE";

    public TransactionCreatedEvent(String type, String payload) {
        this(UUID.randomUUID(), type, payload, Instant.now());
    }
}
