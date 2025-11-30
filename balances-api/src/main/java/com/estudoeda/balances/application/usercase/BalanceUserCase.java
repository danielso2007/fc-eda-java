package com.estudoeda.balances.application.usercase;

import java.util.List;
import java.util.UUID;
import com.estudoeda.balances.application.domain.event.BalanceUpdatedWalletEvent;
import com.estudoeda.balances.application.domain.model.Balance;
import com.estudoeda.balances.application.gateway.BalanceGateway;
import com.estudoeda.balances.application.pagination.Page;
import com.estudoeda.balances.application.pagination.PageRequest;

public class BalanceUserCase {

    private final BalanceGateway balanceGateway;

    public BalanceUserCase(final BalanceGateway gateway) {
        this.balanceGateway = gateway;
    }

    public void receberTransacao(final BalanceUpdatedWalletEvent balanceUpdatedWalletEvent) {
        balanceGateway.save(balanceUpdatedWalletEvent.toModel());
    }

    public List<Balance> findByTransactionId(final UUID transactionId) {
        return balanceGateway.findByAccountId(transactionId);
    }

    public List<Balance> findByAccountId(final UUID accountId) {
        return balanceGateway.findByAccountId(accountId);
    }

    public Page<Balance> findAll(final PageRequest pageRequest) {
        return balanceGateway.findAll(pageRequest);
    }

    public Balance save(final Balance balance) {
        return balanceGateway.save(balance);
    }

    public Balance findById(final UUID id) {
        return balanceGateway.findById(id);
    }

}
