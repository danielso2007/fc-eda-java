package com.estudoeda.walletcore.infrastructure.service;

import org.springframework.stereotype.Service;
import com.estudoeda.walletcore.application.gateway.AccountGateway;
import com.estudoeda.walletcore.application.usercase.AccountUserCase;

@Service
public class AccountService extends AccountUserCase {

    public AccountService(AccountGateway gateway) {
        super(gateway);
    }

}
