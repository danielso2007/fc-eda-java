package com.estudoeda.walletcore.infrastructure.persistence.repository;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import com.estudoeda.walletcore.application.domain.model.Transaction;
import com.estudoeda.walletcore.application.exception.TransactionNotFoundException;
import com.estudoeda.walletcore.application.gateway.TransactionGateway;
import com.estudoeda.walletcore.application.pagination.Page;
import com.estudoeda.walletcore.application.pagination.PageRequest;
import com.estudoeda.walletcore.infrastructure.persistence.entity.TransactionEntity;
import com.estudoeda.walletcore.infrastructure.persistence.mapper.TransactionMapper;

@Repository
public class TransactionGatewayImpl implements TransactionGateway {

    private static final int CAMPO_DA_TABELA = 0;
    private static final int ORDEM = 1;
    private static final String SEPARAR_POR_VIRGULA = ",";
    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;

    public TransactionGatewayImpl(TransactionRepository transactionRepository, TransactionMapper transactionMapper) {
        this.transactionRepository = transactionRepository;
        this.transactionMapper = transactionMapper;
    }

    @Override
    public Transaction create(Transaction transaction) {
        return transactionMapper.toModel(transactionRepository.save(transactionMapper.toDomain(transaction)));
    }

    @Override
    public Transaction findById(UUID id) {
        Optional<TransactionEntity> entity = Optional
                .ofNullable(transactionRepository.findById(id).orElseThrow(() -> new TransactionNotFoundException(id)));
        return transactionMapper.toModel(entity.get());
    }

    @Override
    public void delete(Transaction transaction) {
        transactionRepository.delete(transactionMapper.toDomain(transaction));
    }

    @Override
    public Transaction findByAccountFromId(UUID transactionFromId) {
        return transactionRepository.findByAccountFromId(transactionFromId);
    }

    @Override
    public Transaction findByAccountToId(UUID transactionToId) {
        return transactionRepository.findByAccountToId(transactionToId);
    }

    @Override
    public Page<Transaction> findAll(PageRequest pageRequest) {
        Pageable sp = toSpringPageable(pageRequest);
        var springPage = transactionRepository.findAll(sp);
        var content = springPage.getContent().stream()
                .map(transactionMapper::toModel)
                .toList();
        return new Page<>(content,
                springPage.getNumber(),
                springPage.getSize(),
                springPage.getTotalElements(),
                springPage.getTotalPages());
    }

    private Pageable toSpringPageable(PageRequest pageRequest) {
        if (Objects.isNull(pageRequest.getSort()) || pageRequest.getSort().isBlank()) {
            return org.springframework.data.domain.PageRequest.of(pageRequest.getPage(), pageRequest.getSize());
        }
        String[] parts = pageRequest.getSort().split(SEPARAR_POR_VIRGULA);
        Sort sort = Sort.by(Sort.Direction.fromString(parts[ORDEM].trim()), parts[CAMPO_DA_TABELA].trim());
        return org.springframework.data.domain.PageRequest.of(pageRequest.getPage(), pageRequest.getSize(), sort);
    }

}
