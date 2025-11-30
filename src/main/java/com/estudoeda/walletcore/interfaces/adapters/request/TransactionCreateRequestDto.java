package com.estudoeda.walletcore.interfaces.adapters.request;

import java.math.BigDecimal;
import java.util.UUID;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@JsonPropertyOrder(alphabetic = true)
@Schema(description = "Payload para criar uma transação")
public class TransactionCreateRequestDto {

    @NotNull(message = "A conta de origem não pode ser nulo.")
    @Schema(description = "UUID da conta origem", example = "a1b2c3d4-1111-2222-3333-444455556666", requiredMode = RequiredMode.REQUIRED, type = "string", format = "uuid")
    private UUID accountFromId;

    @NotNull(message = "A conta de destino não pode ser nulo.")
    @Schema(description = "UUID da conta destino", example = "b2c3d4e5-7777-8888-9999-0000aaaabbbb", requiredMode = RequiredMode.REQUIRED, type = "string", format = "uuid")
    private UUID accountToId;

    @NotNull(message = "O valor da transferência não pode ser nulo.")
    @Positive
    @Schema(description = "Valor da transferência (BigDecimal)", example = "150.75", requiredMode = RequiredMode.REQUIRED)
    private BigDecimal amount;

    public TransactionCreateRequestDto() {
    }

    public UUID getAccountFromId() {
        return accountFromId;
    }

    public void setAccountFromId(final UUID accountFromId) {
        this.accountFromId = accountFromId;
    }

    public UUID getAccountToId() {
        return accountToId;
    }

    public void setAccountToId(final UUID accountToId) {
        this.accountToId = accountToId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(final BigDecimal amount) {
        this.amount = amount;
    }
}
