package com.estudoeda.balances.application.gateway;

import java.util.List;
import java.util.UUID;
import com.estudoeda.balances.application.domain.model.Balance;
import com.estudoeda.balances.application.pagination.Page;
import com.estudoeda.balances.application.pagination.PageRequest;

public interface BalanceGateway {
    List<Balance> findByTransactionId(UUID transactionId);

    List<Balance> findByAccountId(UUID accountId);

    Page<Balance> findAll(PageRequest pageRequest);

    Balance save(Balance balance);

    Balance findById(UUID id);

}
