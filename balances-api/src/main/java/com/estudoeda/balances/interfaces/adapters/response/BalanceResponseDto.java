package com.estudoeda.balances.interfaces.adapters.response;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;
import com.estudoeda.balances.application.domain.model.TypeBalanceEnum;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "BalanceResponse", description = "Representação de Balance retornada pela API")
public class BalanceResponseDto {

    @Schema(description = "Identificador do lançamento", example = "3fa85f64-5717-4562-b3fc-2c963f66afa6", format = "uuid")
    private UUID id;

    @Schema(description = "Identificador da conta", example = "3fa85f64-5717-4562-b3fc-2c963f66afa6", format = "uuid")
    private UUID accountId;

    @Schema(description = "Tipo da entrada (DEBIT ou CREDIT)", example = "DEBIT")
    private TypeBalanceEnum entryType;

    @Schema(description = "Valor do lançamento", example = "150.75")
    private BigDecimal amount;

    @Schema(description = "Identificador da transação", example = "5b1e6f70-3a2b-4c8d-9f1a-0b2c3d4e5f60", format = "uuid")
    private UUID transactionId;

    @Schema(description = "Data/hora da transação (ISO-8601)", example = "2025-11-30T10:15:30Z", format = "date-time")
    private Instant transactionAt;

    @Schema(description = "Data/hora de criação do registro (ISO-8601)", example = "2025-11-30T10:16:00Z", format = "date-time")
    private Instant createdAt;

    public BalanceResponseDto() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(final UUID id) {
        this.id = id;
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

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(final Instant createdAt) {
        this.createdAt = createdAt;
    }

}
