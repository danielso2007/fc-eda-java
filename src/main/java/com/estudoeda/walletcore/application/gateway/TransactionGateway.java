package com.estudoeda.walletcore.application.gateway;

import java.util.UUID;
import com.estudoeda.walletcore.application.domain.model.Transaction;
import com.estudoeda.walletcore.application.pagination.Page;
import com.estudoeda.walletcore.application.pagination.PageRequest;

public interface TransactionGateway {
    Transaction findByAccountFromId(UUID transactionFromId);

    Transaction findByAccountToId(UUID transactionToId);

    Transaction create(Transaction transaction);

    Transaction findById(UUID id);

    Page<Transaction> findAll(PageRequest pageRequest);

    void delete(Transaction transaction);
}
