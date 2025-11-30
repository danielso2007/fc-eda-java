package com.estudoeda.walletcore.interfaces.controller;

import java.util.UUID;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import com.estudoeda.walletcore.application.pagination.Page;
import com.estudoeda.walletcore.application.pagination.PageRequest;
import com.estudoeda.walletcore.infrastructure.api.constants.Constants;
import com.estudoeda.walletcore.interfaces.adapters.request.AccountCreateRequestDto;
import com.estudoeda.walletcore.interfaces.adapters.response.AccountResponseDto;
import com.estudoeda.walletcore.interfaces.handlers.ProblemDetail;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Account", description = "Operações com dados de conta")
public interface AccountController {

    @Operation(summary = "Cadastra uma conta.")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Conta criada com sucesso",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = AccountResponseDto.class))),
        @ApiResponse(responseCode = Constants.BAD_REQUEST, description = "Requisição inválida",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class))),
        @ApiResponse(responseCode = Constants.BAD_GATEWAY, description = Constants.BAD_GATEWAY_MSG,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class)))
    })
    ResponseEntity<AccountResponseDto> create(
        @io.swagger.v3.oas.annotations.parameters.RequestBody(
            required = true,
            content = @Content(schema = @Schema(implementation = AccountCreateRequestDto.class)))
        @Valid @RequestBody AccountCreateRequestDto accountCreateRequestDto);

    @Operation(summary = "Obter todas as contas (paginado).")
    @Parameters({
        @Parameter(name = "page", in = ParameterIn.QUERY, description = "Número da página (0-base)", example = "0"),
        @Parameter(name = "size", in = ParameterIn.QUERY, description = "Tamanho da página", example = "10"),
        @Parameter(name = "sort", in = ParameterIn.QUERY, description = "Ordenação: campo,ASC|DESC", example = "clientId,ASC")
    })
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Lista paginada de contas",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = AccountResponseDto.class))),
        @ApiResponse(responseCode = Constants.BAD_GATEWAY, description = Constants.BAD_GATEWAY_MSG,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class)))
    })
    ResponseEntity<Page<AccountResponseDto>> getAll(
        @ParameterObject @PageableDefault(page = 0, size = 10, sort = "clientId", direction = Sort.Direction.ASC) PageRequest pageRequest);

    @Operation(summary = "Obter uma conta por ID informado.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Conta encontrada",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = AccountResponseDto.class))),
        @ApiResponse(responseCode = Constants.NOT_FOUND, description = Constants.REGISTRO_NOT_FOUND_MSG,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class))),
        @ApiResponse(responseCode = Constants.BAD_REQUEST, description = "ID inválido",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class)))
    })
    ResponseEntity<AccountResponseDto> getById(
        @Parameter(description = "UUID da conta", required = true, schema = @Schema(type = "string", format = "uuid"))
        @PathVariable(required = true) UUID id);

    @Operation(summary = "Deletar uma conta por ID.")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Conta deletada com sucesso."),
        @ApiResponse(responseCode = Constants.NOT_FOUND, description = Constants.REGISTRO_NOT_FOUND_MSG,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class)))
    })
    ResponseEntity<Void> delete(
        @Parameter(description = "UUID da conta", required = true, schema = @Schema(type = "string", format = "uuid"))
        @PathVariable(required = true) UUID id);

}
