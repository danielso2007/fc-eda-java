package com.estudoeda.balances.interfaces.adapters.request;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;
import com.estudoeda.balances.application.domain.model.TypeBalanceEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import jakarta.validation.constraints.NotNull;

@Schema(name = "BalanceRequest", description = "Payload para criação/atualização de Balance")
public class BalanceRequestDto {

    @Schema(description = "Identificador da conta", example = "3fa85f64-5717-4562-b3fc-2c963f66afa6", format = "uuid", requiredMode = RequiredMode.REQUIRED)
    @NotNull
    private UUID accountId;

    @Schema(description = "Tipo da entrada (DEBIT ou CREDIT)", example = "DEBIT", requiredMode = RequiredMode.REQUIRED)
    @NotNull
    private TypeBalanceEnum entryType;

    @Schema(description = "Valor do lançamento (positivo)", example = "150.75", requiredMode = RequiredMode.REQUIRED)
    @NotNull
    private BigDecimal amount;

    @Schema(description = "Identificador da transação", example = "5b1e6f70-3a2b-4c8d-9f1a-0b2c3d4e5f60", format = "uuid", requiredMode = RequiredMode.REQUIRED)
    @NotNull
    private UUID transactionId;

    @Schema(description = "Data/hora da transação (ISO-8601)", example = "2025-11-30T10:15:30Z", format = "date-time", requiredMode = RequiredMode.REQUIRED)
    @NotNull
    private Instant transactionAt;

    public BalanceRequestDto() {
    }

    public UUID getAccountId() {
        return accountId;
    }

    public void setAccountId(final UUID accountId) {
        this.accountId = accountId;
    }

    public TypeBalanceEnum getEntryType() {
        return entryType;
    }

    public void setEntryType(final TypeBalanceEnum entryType) {
        this.entryType = entryType;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(final BigDecimal amount) {
        this.amount = amount;
    }

    public UUID getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(final UUID transactionId) {
        this.transactionId = transactionId;
    }

    public Instant getTransactionAt() {
        return transactionAt;
    }

    public void setTransactionAt(final Instant transactionAt) {
        this.transactionAt = transactionAt;
    }

}
