package com.estudoeda.walletcore.interfaces.controller.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.estudoeda.walletcore.infrastructure.service.TransactionService;
import com.estudoeda.walletcore.interfaces.adapters.mapper.TransactionAdapterMapper;
import com.estudoeda.walletcore.interfaces.adapters.request.TransactionCreateRequestDto;
import com.estudoeda.walletcore.interfaces.adapters.response.TransactionResponseDto;
import com.estudoeda.walletcore.interfaces.controller.TransactionController;
import jakarta.validation.Valid;

@RestController
@Validated
@RequestMapping(path = "/transactions", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
public class TransactionControllerImpl implements TransactionController {

    private static final Logger logger = LoggerFactory.getLogger(TransactionController.class);

    private final TransactionService transactionService;
    private final TransactionAdapterMapper transactionAdapterMapper;

    public TransactionControllerImpl(TransactionService transactionService, TransactionAdapterMapper transactionAdapterMapper) {
        this.transactionService = transactionService;
        this.transactionAdapterMapper = transactionAdapterMapper;
    }

    @Override
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TransactionResponseDto> realizar(@Valid TransactionCreateRequestDto transactionCreateRequestDto) {
        logger.info("Iniciando transação...");
        var transaction = transactionService.create(transactionCreateRequestDto.getAccountFromId(), transactionCreateRequestDto.getAccountToId(), transactionCreateRequestDto.getAmount());
        return ResponseEntity.status(HttpStatus.CREATED).body(transactionAdapterMapper.toResponse(transaction));
    }
    
}
