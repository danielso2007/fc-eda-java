package com.estudoeda.walletcore.application.exception;

import java.io.Serial;

public abstract class DomainException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 6712625194244543989L;

    public DomainException(String message) {
        super(message);
    }

    public DomainException(String message, Throwable throwable) {
        super(message, throwable);
    }
}