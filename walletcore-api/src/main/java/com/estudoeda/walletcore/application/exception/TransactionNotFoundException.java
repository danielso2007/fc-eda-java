package com.estudoeda.walletcore.application.exception;

import java.util.UUID;

public class TransactionNotFoundException extends DomainException {
    private static final long serialVersionUID = 1L;

    public TransactionNotFoundException(final UUID id) {
        super("Transaction not found: " + id);
    }
}
