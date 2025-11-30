package com.estudoeda.walletcore.application.gateway;

import java.util.UUID;
import com.estudoeda.walletcore.application.domain.model.Account;
import com.estudoeda.walletcore.application.pagination.Page;
import com.estudoeda.walletcore.application.pagination.PageRequest;

public interface AccountGateway {
    Account findByClientId(UUID clientId);

    Page<Account> findAll(PageRequest pageRequest);

    Account save(Account account);

    Account update(UUID id, Account account);

    Account findById(UUID id);

    void delete(Account account);
}
