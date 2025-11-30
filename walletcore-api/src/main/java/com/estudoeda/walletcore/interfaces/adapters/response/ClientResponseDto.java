package com.estudoeda.walletcore.interfaces.adapters.response;

import java.util.UUID;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Representação de cliente")
public class ClientResponseDto {

    @Schema(description = "Identificador do cliente", example = "53a19fa9-39f4-4bb7-9e67-c6e29f0b8e02", accessMode = Schema.AccessMode.READ_ONLY)
    private UUID id;

    @Schema(description = "Nome do cliente", example = "João da Silva")
    private String name;

    @Schema(description = "E-mail do cliente", example = "joao.silva@example.com")
    private String email;

    public ClientResponseDto() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(final UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }
}
