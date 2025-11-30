package com.estudoeda.walletcore.application.exception;

public class AmountException extends DomainException {
    private static final long serialVersionUID = 1L;

    public AmountException(String message) {
        super(message);
    }

    public AmountException(String message, Throwable throwable) {
        super(message, throwable);
    }

}