package com.estudoeda.walletcore.infrastructure.persistence.repository;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import com.estudoeda.walletcore.application.domain.model.Account;
import com.estudoeda.walletcore.application.exception.AccountNotFoundException;
import com.estudoeda.walletcore.application.gateway.AccountGateway;
import com.estudoeda.walletcore.application.pagination.Page;
import com.estudoeda.walletcore.application.pagination.PageRequest;
import com.estudoeda.walletcore.infrastructure.persistence.entity.AccountEntity;
import com.estudoeda.walletcore.infrastructure.persistence.mapper.AccountMapper;

@Repository
public class AccountGatewayImpl implements AccountGateway {

    private static final int CAMPO_DA_TABELA = 0;
    private static final int ORDEM = 1;
    private static final String SEPARAR_POR_VIRGULA = ",";
    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    public AccountGatewayImpl(AccountRepository accountRepository, AccountMapper accountMapper) {
        this.accountRepository = accountRepository;
        this.accountMapper = accountMapper;
    }

    @Override
    public Account findByClientId(UUID clientId) {
        return accountMapper.toModel(accountRepository.findByClientId(clientId));
    }

    @Override
    public Account save(Account account) {
        return accountMapper.toModel(accountRepository.save(accountMapper.toDomain(account)));
    }

    @Override
    public Account update(UUID id, Account account) {
        AccountEntity existingEntity = accountRepository.findById(id)
                .orElseThrow(() -> new AccountNotFoundException(id));

        accountMapper.updateEntityFromDomain(account, existingEntity);

        accountRepository.save(existingEntity);

        return accountMapper.toModel(existingEntity);
    }

    @Override
    public Account findById(UUID id) {
        Optional<AccountEntity> entity = Optional
                .ofNullable(accountRepository.findById(id).orElseThrow(() -> new AccountNotFoundException(id)));
        return accountMapper.toModel(entity.get());
    }

    @Override
    public void delete(Account account) {
        accountRepository.delete(accountMapper.toDomain(account));
    }

    @Override
    public Page<Account> findAll(PageRequest pageRequest) {
        Pageable sp = toSpringPageable(pageRequest);
        var springPage = accountRepository.findAll(sp);
        var content = springPage.getContent().stream()
                .map(accountMapper::toModel)
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
