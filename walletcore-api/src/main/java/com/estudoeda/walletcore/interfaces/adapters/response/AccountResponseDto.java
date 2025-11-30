package com.estudoeda.walletcore.interfaces.adapters.response;

import java.util.UUID;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;

@Schema(description = "Representação de conta")
public class AccountResponseDto {

    @Schema(description = "Identificador da conta", example = "8b74222c-3db0-4c1b-9c73-2db7d0c02e45", accessMode = Schema.AccessMode.READ_ONLY)
    private UUID id;

    @Schema(description = "Identificador do cliente", example = "c19cda51-b2c5-4c79-9239-83b830d9bb47", requiredMode = RequiredMode.REQUIRED)
    private UUID clientId;

    @Schema(description = "Saldo", example = "150.75", requiredMode = RequiredMode.REQUIRED)
    private Double balance;

    public AccountResponseDto() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getClientId() {
        return clientId;
    }

    public void setClientId(UUID clientId) {
        this.clientId = clientId;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }
}
