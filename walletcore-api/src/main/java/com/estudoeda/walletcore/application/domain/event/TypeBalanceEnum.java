package com.estudoeda.walletcore.application.domain.event;

import java.util.Map;

/**
 * Representa o tipo de balanço de uma transação.
 */
public enum TypeBalanceEnum {

    DEBIT("DEBIT", -1),
    CREDIT("CREDIT", 1);

    private static final Map<String, TypeBalanceEnum> BY_CODE = Map.of(DEBIT.code, DEBIT, CREDIT.code, CREDIT);

    private final String code;
    private final int factor;

    TypeBalanceEnum(String code, int factor) {
        this.code = code;
        this.factor = factor;
    }

    public String getCode() {
        return code;
    }

    public int getFactor() {
        return factor;
    }

    public static TypeBalanceEnum fromCode(String code) {
        if (code == null) {
            throw new IllegalArgumentException("code cannot be null");
        }
        TypeBalanceEnum value = BY_CODE.get(code.toUpperCase());
        if (value == null) {
            throw new IllegalArgumentException("unknown TypeBalanceEnum code: " + code);
        }
        return value;
    }
}
