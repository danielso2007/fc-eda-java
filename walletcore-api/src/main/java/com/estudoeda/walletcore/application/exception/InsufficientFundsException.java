package com.estudoeda.walletcore.application.exception;

public class InsufficientFundsException extends DomainException {
    private static final long serialVersionUID = 1L;

    public InsufficientFundsException(String message) {
        super(message);
    }

    public InsufficientFundsException(String message, Throwable throwable) {
        super(message, throwable);
    }

}
