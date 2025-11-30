package com.estudoeda.walletcore.application.exception;

import java.util.UUID;

public class ClientNotFoundException extends DomainException {
    private static final long serialVersionUID = 1L;

    public ClientNotFoundException(final UUID id) {
        super("Client not found: " + id);
    }
}
