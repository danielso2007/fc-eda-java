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
import com.estudoeda.walletcore.interfaces.adapters.request.ClientCreateRequestDto;
import com.estudoeda.walletcore.interfaces.adapters.response.ClientResponseDto;
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

@Tag(name = "Client", description = "Operações com dados de cliente")
public interface ClientController {

    @Operation(summary = "Cadastra um cliente.")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Cliente salvo com sucesso.",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = ClientResponseDto.class))),
        @ApiResponse(responseCode = Constants.BAD_REQUEST, description = "Requisição inválida",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class))),
        @ApiResponse(responseCode = Constants.BAD_GATEWAY, description = Constants.BAD_GATEWAY_MSG,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class)))
    })
    ResponseEntity<ClientResponseDto> save(@io.swagger.v3.oas.annotations.parameters.RequestBody(
            required = true,
            content = @Content(schema = @Schema(implementation = ClientCreateRequestDto.class)))
        @Valid @RequestBody ClientCreateRequestDto clientCreateRequestDto);

    @Operation(summary = "Obter todos os clientes (paginado).")
    @Parameters({
        @Parameter(name = "page", in = ParameterIn.QUERY, description = "Número da página (0-base)", example = "0"),
        @Parameter(name = "size", in = ParameterIn.QUERY, description = "Tamanho da página", example = "10"),
        @Parameter(name = "sort", in = ParameterIn.QUERY, description = "Ordenação: campo,ASC|DESC", example = "name,ASC")
    })
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Lista paginada de clientes",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(description = "Lista de clientes", implementation = ClientResponseDto.class))),
        @ApiResponse(responseCode = Constants.BAD_GATEWAY, description = Constants.BAD_GATEWAY_MSG,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class)))
    })
    ResponseEntity<Page<ClientResponseDto>> getAll(
        @ParameterObject @PageableDefault(page = 0, size = 10, sort = "name", direction = Sort.Direction.ASC) PageRequest pageRequest);

    @Operation(summary = "Obter um cliente por ID informado.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Cliente encontrado",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ClientResponseDto.class))),
        @ApiResponse(responseCode = Constants.NOT_FOUND, description = Constants.REGISTRO_NOT_FOUND_MSG,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class))),
        @ApiResponse(responseCode = Constants.BAD_REQUEST, description = "ID inválido",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class)))
    })
    ResponseEntity<ClientResponseDto> getById(
        @Parameter(description = "UUID do cliente", required = true, schema = @Schema(type = "string", format = "uuid"))
        @PathVariable(required = true) UUID id);

    @Operation(summary = "Deletar um cliente por ID.")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Cliente deletado com sucesso."),
        @ApiResponse(responseCode = Constants.NOT_FOUND, description = Constants.REGISTRO_NOT_FOUND_MSG,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class)))
    })
    ResponseEntity<Void> delete(
        @Parameter(description = "UUID do cliente", required = true, schema = @Schema(type = "string", format = "uuid"))
        @PathVariable(required = true) UUID id);

    @Operation(summary = "Atualizar um cliente.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Cliente atualizado com sucesso.",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ClientResponseDto.class))),
        @ApiResponse(responseCode = Constants.BAD_REQUEST, description = "Requisição inválida",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class))),
        @ApiResponse(responseCode = Constants.NOT_FOUND, description = Constants.REGISTRO_NOT_FOUND_MSG,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class)))
    })
    ResponseEntity<ClientResponseDto> update(
        @Parameter(description = "UUID do cliente", required = true, schema = @Schema(type = "string", format = "uuid"))
        @PathVariable(required = true) UUID id,
        @io.swagger.v3.oas.annotations.parameters.RequestBody(
            required = true,
            content = @Content(schema = @Schema(implementation = ClientCreateRequestDto.class)))
        @Valid @RequestBody ClientCreateRequestDto clientCreateRequestDto);
}
