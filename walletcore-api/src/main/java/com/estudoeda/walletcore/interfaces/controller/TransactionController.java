package com.estudoeda.walletcore.interfaces.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import com.estudoeda.walletcore.infrastructure.api.constants.Constants;
import com.estudoeda.walletcore.interfaces.adapters.request.TransactionCreateRequestDto;
import com.estudoeda.walletcore.interfaces.adapters.response.TransactionResponseDto;
import com.estudoeda.walletcore.interfaces.handlers.ProblemDetail;
import org.springframework.web.bind.annotation.RequestBody;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Transaction", description = "Operações com dados de transação")
public interface TransactionController {

    @Operation(summary = "Realizar uma transação entre contas.")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Transação realizada com sucesso",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = TransactionResponseDto.class))),
        @ApiResponse(responseCode = Constants.BAD_REQUEST, description = "Requisição inválida / validação falhou",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class))),
        @ApiResponse(responseCode = Constants.NOT_FOUND, description = Constants.REGISTRO_NOT_FOUND_MSG,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class))),
        @ApiResponse(responseCode = Constants.BAD_GATEWAY, description = Constants.BAD_GATEWAY_MSG,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class)))
    })
    ResponseEntity<TransactionResponseDto> realizar(
        @io.swagger.v3.oas.annotations.parameters.RequestBody(required = true, content = @Content(schema = @Schema(implementation = TransactionCreateRequestDto.class)))
        @RequestBody
        @Valid TransactionCreateRequestDto transactionCreateRequestDto
    );
}
