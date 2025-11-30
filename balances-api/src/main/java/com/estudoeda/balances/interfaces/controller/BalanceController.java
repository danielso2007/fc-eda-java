package com.estudoeda.balances.interfaces.controller;

import java.util.List;
import java.util.UUID;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import com.estudoeda.balances.application.pagination.Page;
import com.estudoeda.balances.application.pagination.PageRequest;
import com.estudoeda.balances.infrastructure.api.constants.Constants;
import com.estudoeda.balances.interfaces.adapters.response.BalanceResponseDto;
import com.estudoeda.balances.interfaces.handlers.ProblemDetail;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Balance", description = "Operações com dados de balanços financeiros")
public interface BalanceController {

    @Operation(summary = "Obter todos os balanços financeiros (paginado).")
    @Parameters({
        @Parameter(name = "page", in = ParameterIn.QUERY, description = "Número da página (0-base)", example = "0"),
        @Parameter(name = "size", in = ParameterIn.QUERY, description = "Tamanho da página", example = "10"),
        @Parameter(name = "sort", in = ParameterIn.QUERY, description = "Ordenação: campo,ASC|DESC", example = "createdAt,ASC")
    })
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Lista paginada de balanços financeiros",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(description = "Lista de balanços financeiros", implementation = BalanceResponseDto.class))),
        @ApiResponse(responseCode = Constants.BAD_GATEWAY, description = Constants.BAD_GATEWAY_MSG,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class)))
    })
    ResponseEntity<Page<BalanceResponseDto>> getAll(
        @ParameterObject @PageableDefault(page = 0, size = 10, sort = "name", direction = Sort.Direction.ASC) PageRequest pageRequest);

    @Operation(summary = "Obter um balanço financeiro pelo código da conta")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Balanço financeiro encontrado",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = BalanceResponseDto.class))),
        @ApiResponse(responseCode = Constants.NOT_FOUND, description = Constants.REGISTRO_NOT_FOUND_MSG,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class))),
        @ApiResponse(responseCode = Constants.BAD_REQUEST, description = "ID inválido",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class)))
    })
    ResponseEntity<List<BalanceResponseDto>> getByAccountId(
        @Parameter(description = "UUID da conta", required = true, schema = @Schema(type = "string", format = "uuid", example = "c1d2e3f4-9999-aaaa-bbbb-000000000009"))
        @PathVariable(required = true) UUID accountId);
    
}
