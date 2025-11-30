package com.estudoeda.walletcore.application.exception;

import java.util.UUID;

public class AccountNotFoundException extends DomainException {
    private static final long serialVersionUID = 1L;

    public AccountNotFoundException(final UUID id) {
        super("Account not found: " + id);
    }
}
