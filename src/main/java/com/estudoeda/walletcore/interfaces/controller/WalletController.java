package com.estudoeda.walletcore.interfaces.controller;

import org.springframework.http.ResponseEntity;
import com.estudoeda.walletcore.application.domain.event.WalletEvent;
import com.estudoeda.walletcore.interfaces.handlers.ProblemDetail;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Wallet", description = "Operações com Wallet")
public interface WalletController {

    String ERRO_MOMENTANEO_POR_FAVOR_MSG = "Erro momentaneo, por favor tente mais tarde...";
    String SUCESSO = "200";
    String BAD_REQUEST = "400";
    String NOT_FOUND = "404";
    String BAD_GATEWAY = "502";
    String APPLICATION_JSON_CHARSET_UTF_8 = "application/json;charset=utf-8";
    String BAD_GATEWAY_MSG = "Bad Gateway.";
    String CURSO_NOT_FOUND_MSG = "Curso not found.";
    String CONTENT_TYPE_JSON = "application/json";
    String INVALID_ID_SUPPLIED_MSG = "Invalid id supplied.";

    @Operation(summary = "Cadastra um Wallet.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = SUCESSO, description = "Wallet salvo com sucesso.",
                content = { @Content(mediaType = CONTENT_TYPE_JSON, schema = @Schema(implementation = WalletEvent.class)) }),
        @ApiResponse(responseCode = BAD_REQUEST, description = INVALID_ID_SUPPLIED_MSG,
                content = { @Content(mediaType = CONTENT_TYPE_JSON, schema = @Schema(implementation = ProblemDetail.class)) }),
        @ApiResponse(responseCode = NOT_FOUND, description = CURSO_NOT_FOUND_MSG,
                content = { @Content(mediaType = CONTENT_TYPE_JSON, schema = @Schema(implementation = ProblemDetail.class)) }),
        @ApiResponse(responseCode = BAD_GATEWAY, description = BAD_GATEWAY_MSG,
                content = { @Content(mediaType = CONTENT_TYPE_JSON, schema = @Schema(implementation = ProblemDetail.class)) })
    })
    ResponseEntity<Void> create(String payload);

}