package com.estudoeda.walletcore.application.domain.event;

import java.time.Instant;
import java.util.UUID;

/**
 * Representa um evento de atualização de saldo.
 *
 * @param type       O type do evento.
 * @param payload    O conteúdo do evento (o objeto que contém a atualização).
 * @param occurredAt O timestamp de quando o evento foi criado.
 */
public record BalanceUpdatedWallet(
        UUID id,
        String type,
        Object payload,
        Instant occurredAt) {

    public static final String TYPE_BALANCE_UPDATE = "BALANCE_UPDATE";

    public BalanceUpdatedWallet(String type, String payload) {
        this(UUID.randomUUID(), type, payload, Instant.now());
    }
}