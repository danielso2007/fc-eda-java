package com.estudoeda.walletcore.interfaces.adapters.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;

@JsonPropertyOrder(alphabetic = true)
@Schema(description = "Payload para criação de cliente")
public class ClientCreateRequestDto {

    @NotBlank(message = "O nome não pode ser em branco.")
    @NotNull(message = "O nome não pode ser nulo.")
    @Size(min = 2, max = 120)
    @Schema(description = "Nome do cliente", example = "João da Silva", requiredMode = RequiredMode.REQUIRED)
    private String name;

    @Email
    @NotBlank(message = "O e-mail não pode ser em branco.")
    @NotNull(message = "O e-mail não pode ser nulo.")
    @Size(max = 180)
    @Schema(description = "E-mail do cliente", example = "joao.silva@example.com", requiredMode = RequiredMode.REQUIRED)
    private String email;

    public ClientCreateRequestDto() {
    }

    public ClientCreateRequestDto(final String name, final String email) {
        this.name = name;
        this.email = email;
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
