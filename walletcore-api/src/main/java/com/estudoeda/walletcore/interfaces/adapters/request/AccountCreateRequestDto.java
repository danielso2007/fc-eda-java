package com.estudoeda.walletcore.interfaces.adapters.request;

import java.util.UUID;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import jakarta.validation.constraints.NotNull;

@JsonPropertyOrder(alphabetic = true)
@Schema(description = "Payload para criação de conta")
public class AccountCreateRequestDto {

    @NotNull(message = "O ID do cliente da conta não pode ser nulo.")
    @Schema(description = "Identificador do cliente", example = "c19cda51-b2c5-4c79-9239-83b830d9bb47", requiredMode = RequiredMode.REQUIRED)
    private UUID clientId;

    public AccountCreateRequestDto() {
    }

    public UUID getClientId() {
        return clientId;
    }

    public void setClientId(UUID clientId) {
        this.clientId = clientId;
    }

}
