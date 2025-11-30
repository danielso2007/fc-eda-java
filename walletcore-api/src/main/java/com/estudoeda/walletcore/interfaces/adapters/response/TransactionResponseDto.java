package com.estudoeda.walletcore.interfaces.adapters.response;

import java.math.BigDecimal;
import java.util.UUID;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Representação de transação")
public class TransactionResponseDto {

    @Schema(description = "UUID da transação", example = "d3e4f5a6-1234-5678-90ab-cdef12345678", accessMode = Schema.AccessMode.READ_ONLY, type = "string", format = "uuid")
    private UUID id;

    @Schema(description = "UUID da conta origem", example = "a1b2c3d4-1111-2222-3333-444455556666", type = "string", format = "uuid")
    private UUID accountFromId;

    @Schema(description = "UUID da conta destino", example = "b2c3d4e5-7777-8888-9999-0000aaaabbbb", type = "string", format = "uuid")
    private UUID accountToId;

    @Schema(description = "Valor da transação", example = "150.75")
    private BigDecimal amount;

    public TransactionResponseDto() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(final UUID id) {
        this.id = id;
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
