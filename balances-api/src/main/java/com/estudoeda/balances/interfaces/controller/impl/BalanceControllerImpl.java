package com.estudoeda.balances.interfaces.controller.impl;

import java.util.List;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.estudoeda.balances.application.pagination.Page;
import com.estudoeda.balances.application.pagination.PageRequest;
import com.estudoeda.balances.infrastructure.service.BalanceService;
import com.estudoeda.balances.interfaces.adapters.mapper.BalanceAdapterMapper;
import com.estudoeda.balances.interfaces.adapters.response.BalanceResponseDto;
import com.estudoeda.balances.interfaces.controller.BalanceController;

@RestController
@Validated
@RequestMapping(path = "/balances", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
public class BalanceControllerImpl implements BalanceController {

    private final BalanceService balanceService;
    private final BalanceAdapterMapper balanceAdapterMapper;

    public BalanceControllerImpl(BalanceService balanceService, BalanceAdapterMapper balanceAdapterMapper) {
        this.balanceService = balanceService;
        this.balanceAdapterMapper = balanceAdapterMapper;
    }

    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.ALL_VALUE)
    public ResponseEntity<Page<BalanceResponseDto>> getAll(PageRequest pageRequest) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(balanceAdapterMapper.toResponsePageDto(balanceService.findAll(pageRequest)));
    }

    @Override
    @GetMapping(path = "/{accountId}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.ALL_VALUE)
    public ResponseEntity<List<BalanceResponseDto>> getByAccountId(UUID accountId) {
        return ResponseEntity.status(HttpStatus.OK).body(balanceAdapterMapper.toResponse(balanceService.findByAccountId(accountId)));
    }

}
