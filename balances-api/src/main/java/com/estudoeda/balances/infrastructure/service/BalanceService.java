package com.estudoeda.balances.infrastructure.service;

import org.springframework.stereotype.Service;
import com.estudoeda.balances.application.gateway.BalanceGateway;
import com.estudoeda.balances.application.usercase.BalanceUserCase;

@Service
public class BalanceService extends BalanceUserCase {

    public BalanceService(BalanceGateway gateway) {
        super(gateway);
    }

}
