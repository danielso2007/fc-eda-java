package com.estudoeda.walletcore.interfaces.controller.impl;

import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.estudoeda.walletcore.application.pagination.Page;
import com.estudoeda.walletcore.application.pagination.PageRequest;
import com.estudoeda.walletcore.infrastructure.service.ClientService;
import com.estudoeda.walletcore.interfaces.adapters.mapper.ClientAdapterMapper;
import com.estudoeda.walletcore.interfaces.adapters.request.ClientCreateRequestDto;
import com.estudoeda.walletcore.interfaces.adapters.response.ClientResponseDto;
import com.estudoeda.walletcore.interfaces.controller.ClientController;
import jakarta.validation.Valid;

@RestController
@Validated
@RequestMapping(path = "/clients", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
public class ClientControllerImpl implements ClientController {

    private static final Logger logger = LoggerFactory.getLogger(ClientController.class);

    private final ClientService clientService;
    private final ClientAdapterMapper clientAdapterMapper;

    public ClientControllerImpl(ClientService clientService, ClientAdapterMapper clientAdapterMapper) {
        this.clientService = clientService;
        this.clientAdapterMapper = clientAdapterMapper;
    }

    @Override
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ClientResponseDto> save(@Valid ClientCreateRequestDto clientCreateRequestDto) {
        var client = clientService.save(clientAdapterMapper.toModel(clientCreateRequestDto));
        if (logger.isInfoEnabled()) {
            logger.info("Cliente {} criado.", client.getId());
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(clientAdapterMapper.toResponse(client));
    }

    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.ALL_VALUE)
    public ResponseEntity<Page<ClientResponseDto>> getAll(PageRequest pageRequest) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(clientAdapterMapper.toResponsePageDto(clientService.findAll(pageRequest)));
    }

    @Override
    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.ALL_VALUE)
    public ResponseEntity<ClientResponseDto> getById(UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(clientAdapterMapper.toResponse(clientService.findById(id)));
    }

    @Override
    @DeleteMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.ALL_VALUE)
    public ResponseEntity<Void> delete(UUID id) {
        clientService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    @PutMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ClientResponseDto> update(UUID id, @Valid ClientCreateRequestDto clientCreateRequestDto) {
        var client = clientService.update(id, clientAdapterMapper.toModel(clientCreateRequestDto));
        logger.info("Cliente atualizado com sucesso!");
        return ResponseEntity.status(HttpStatus.CREATED).body(clientAdapterMapper.toResponse(client));
    }

}
