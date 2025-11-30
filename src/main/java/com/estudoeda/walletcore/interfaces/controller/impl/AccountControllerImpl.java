package com.estudoeda.walletcore.interfaces.controller.impl;

import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.estudoeda.walletcore.application.pagination.Page;
import com.estudoeda.walletcore.application.pagination.PageRequest;
import com.estudoeda.walletcore.infrastructure.service.AccountService;
import com.estudoeda.walletcore.interfaces.adapters.mapper.AccountAdapterMapper;
import com.estudoeda.walletcore.interfaces.adapters.request.AccountCreateRequestDto;
import com.estudoeda.walletcore.interfaces.adapters.response.AccountResponseDto;
import com.estudoeda.walletcore.interfaces.controller.AccountController;
import jakarta.validation.Valid;

@RestController
@Validated
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(path = "/accounts", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
public class AccountControllerImpl implements AccountController {

    private static final Logger logger = LoggerFactory.getLogger(AccountController.class);

    private final AccountService accountService;
    private final AccountAdapterMapper accountAdapterMapper;

    public AccountControllerImpl(AccountService accountService, AccountAdapterMapper accountAdapterMapper) {
        this.accountService = accountService;
        this.accountAdapterMapper = accountAdapterMapper;
    }

    @Override
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AccountResponseDto> create(@Valid AccountCreateRequestDto accountCreateRequestDto) {
        var account = accountService.create(accountCreateRequestDto.getClientId());
        if (logger.isInfoEnabled()) {
            logger.info("Criando conta para o cliente {}", accountCreateRequestDto.getClientId());
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(accountAdapterMapper.toResponse(account));
    }

    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.ALL_VALUE)
    public ResponseEntity<Page<AccountResponseDto>> getAll(PageRequest pageRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(accountAdapterMapper.toResponsePageDto(accountService.findAll(pageRequest)));
    }

    @Override
    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.ALL_VALUE)
    public ResponseEntity<AccountResponseDto> getById(UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(accountAdapterMapper.toResponse(accountService.findById(id)));
    }

    @Override
    @DeleteMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.ALL_VALUE)
    public ResponseEntity<Void> delete(UUID id) {
        accountService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
