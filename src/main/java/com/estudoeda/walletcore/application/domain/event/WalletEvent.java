package com.estudoeda.walletcore.application.domain.event;

import java.time.Instant;
import java.util.UUID;

public record WalletEvent(UUID id, String type, String payload, Instant occurredAt) {}
