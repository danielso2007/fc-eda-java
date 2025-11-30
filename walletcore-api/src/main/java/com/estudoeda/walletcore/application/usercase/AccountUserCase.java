package com.estudoeda.walletcore.application.usercase;

import java.util.UUID;
import com.estudoeda.walletcore.application.domain.model.Account;
import com.estudoeda.walletcore.application.gateway.AccountGateway;
import com.estudoeda.walletcore.application.pagination.Page;
import com.estudoeda.walletcore.application.pagination.PageRequest;

public class AccountUserCase {

    private final AccountGateway accountGateway;

    public AccountUserCase(final AccountGateway gateway) {
        this.accountGateway = gateway;
    }

    public Account findByClientId(UUID id) {
        return accountGateway.findByClientId(id);
    }

    public Page<Account> findAll(PageRequest pageRequest) {
        return accountGateway.findAll(pageRequest);
    }

    public Account create(final UUID clientId) {
        var account = Account.createAccount(clientId);
        
        return accountGateway.save(account);
    }

    public Account findById(final UUID id) {
        return accountGateway.findById(id);
    }

    public void delete(final UUID id) {
        accountGateway.delete(new Account(id));
    }

}
