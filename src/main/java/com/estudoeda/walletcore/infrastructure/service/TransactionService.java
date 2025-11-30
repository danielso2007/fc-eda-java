package com.estudoeda.walletcore.infrastructure.service;

import org.springframework.stereotype.Service;
import com.estudoeda.walletcore.application.gateway.AccountGateway;
import com.estudoeda.walletcore.application.gateway.TransactionGateway;
import com.estudoeda.walletcore.application.usercase.TransactionUserCase;

@Service
public class TransactionService extends TransactionUserCase {

    public TransactionService(final TransactionGateway transactionGateway, final AccountGateway accountGateway) {
        super(transactionGateway, accountGateway);
    }

}
