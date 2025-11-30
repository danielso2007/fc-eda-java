package com.estudoeda.walletcore.infrastructure.service;

import org.springframework.stereotype.Service;
import com.estudoeda.walletcore.application.gateway.ClientGateway;
import com.estudoeda.walletcore.application.usercase.ClientUserCase;

@Service
public class ClientService extends ClientUserCase {

    public ClientService(ClientGateway gateway) {
        super(gateway);
    }

}
