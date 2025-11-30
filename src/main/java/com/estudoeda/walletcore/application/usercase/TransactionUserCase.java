package com.estudoeda.walletcore.application.usercase;

import java.math.BigDecimal;
import java.util.UUID;
import com.estudoeda.walletcore.application.domain.model.Account;
import com.estudoeda.walletcore.application.domain.model.Transaction;
import com.estudoeda.walletcore.application.gateway.AccountGateway;
import com.estudoeda.walletcore.application.gateway.TransactionGateway;
import com.estudoeda.walletcore.application.pagination.Page;
import com.estudoeda.walletcore.application.pagination.PageRequest;

public class TransactionUserCase {

    private final TransactionGateway transactionGateway;
    private final AccountGateway accountGateway;

    public TransactionUserCase(final TransactionGateway transactionGateway, final AccountGateway accountGateway) {
        this.transactionGateway = transactionGateway;
        this.accountGateway = accountGateway;
    }

    public Transaction findByAccountFromId(UUID id) {
        return transactionGateway.findByAccountFromId(id);
    }

    public Transaction findByAccountToId(UUID id) {
        return transactionGateway.findByAccountToId(id);
    }

    public Page<Transaction> findAll(PageRequest pageRequest) {
        return transactionGateway.findAll(pageRequest);
    }

    public Transaction create(final UUID accountFromId, final UUID accountToId, BigDecimal amount) {
        Transaction transaction = criarTransacao(accountFromId, accountToId, amount);
        return transactionGateway.create(transaction);
    }

    private Transaction criarTransacao(final UUID accountFromId, final UUID accountToId, BigDecimal amount) {
        var accountFrom = accountGateway.findById(accountFromId);
        var accountTo = accountGateway.findById(accountToId);
        var entity = Transaction.newTransaction(accountFrom, accountTo, amount);
        atualizarSaldo(accountFrom, accountTo);
        return entity;
    }

    private void atualizarSaldo(Account accountFrom, Account accountTo) {
        accountGateway.update(accountFrom.getId(), accountFrom);
        accountGateway.update(accountTo.getId(), accountTo);
    }

    public Transaction findById(final UUID id) {
        return transactionGateway.findById(id);
    }

    public void delete(final UUID id) {
        transactionGateway.delete(new Transaction(id));
    }

}
