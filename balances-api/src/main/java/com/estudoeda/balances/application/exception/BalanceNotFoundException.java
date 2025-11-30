package com.estudoeda.balances.application.exception;

import java.io.Serial;
import java.util.UUID;

public class BalanceNotFoundException extends DomainException {

    @Serial
    private static final long serialVersionUID = 6772675191999543989L;

    public BalanceNotFoundException(final UUID id) {
        super("Balance not found: " + id);
    }
}