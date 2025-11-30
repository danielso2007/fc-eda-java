package com.estudoeda.walletcore.application.exception;

import java.io.Serial;

public class BusinessException extends DomainException {

    @Serial
    private static final long serialVersionUID = 6772675191244543989L;

    public BusinessException(String msg) {
        super(msg);
    }
}