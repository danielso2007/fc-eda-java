package com.estudoeda.balances.infrastructure.persistence.repository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import com.estudoeda.balances.application.domain.model.Balance;
import com.estudoeda.balances.application.exception.BalanceNotFoundException;
import com.estudoeda.balances.application.gateway.BalanceGateway;
import com.estudoeda.balances.application.pagination.Page;
import com.estudoeda.balances.application.pagination.PageRequest;
import com.estudoeda.balances.infrastructure.persistence.entity.BalanceEntity;
import com.estudoeda.balances.infrastructure.persistence.mapper.BalanceMapper;

@Repository
public class BalanceGatewayImpl implements BalanceGateway {

    private static final int CAMPO_DA_TABELA = 0;
    private static final int ORDEM = 1;
    private static final String SEPARAR_POR_VIRGULA = ",";
    private final BalanceRepository balanceRepository;
    private final BalanceMapper balanceMapper;

    public BalanceGatewayImpl(final BalanceRepository balanceRepository, final BalanceMapper balanceMapper) {
        this.balanceRepository = balanceRepository;
        this.balanceMapper = balanceMapper;
    }

    @Override
    public Balance save(final Balance balance) {
        return balanceMapper.toModel(balanceRepository.save(balanceMapper.toDomain(balance)));
    }

    @Override
    public Balance findById(final UUID id) {
        final Optional<BalanceEntity> entity = Optional
                .ofNullable(balanceRepository.findById(id).orElseThrow(() -> new BalanceNotFoundException(id)));
        return balanceMapper.toModel(entity.get());
    }

    @Override
    public List<Balance> findByTransactionId(UUID transactionId) {
        return balanceMapper.toModel(balanceRepository.findByTransactionId(transactionId));
    }

    @Override
    public List<Balance> findByAccountId(UUID accountId) {
        return balanceMapper.toModel(balanceRepository.findByAccountId(accountId));
    }

    @Override
    public Page<Balance> findAll(final PageRequest pageRequest) {
        final Pageable sp = toSpringPageable(pageRequest);
        final var springPage = balanceRepository.findAll(sp);
        final var content = springPage.getContent().stream()
                .map(balanceMapper::toModel)
                .toList();
        return new Page<>(content,
                springPage.getNumber(),
                springPage.getSize(),
                springPage.getTotalElements(),
                springPage.getTotalPages());
    }

    private Pageable toSpringPageable(final PageRequest pageRequest) {
        if (Objects.isNull(pageRequest.getSort()) || pageRequest.getSort().isBlank()) {
            return org.springframework.data.domain.PageRequest.of(pageRequest.getPage(), pageRequest.getSize());
        }
        final String[] parts = pageRequest.getSort().split(SEPARAR_POR_VIRGULA);
        final Sort sort = Sort.by(Sort.Direction.fromString(parts[ORDEM].trim()), parts[CAMPO_DA_TABELA].trim());
        return org.springframework.data.domain.PageRequest.of(pageRequest.getPage(), pageRequest.getSize(), sort);
    }

}
