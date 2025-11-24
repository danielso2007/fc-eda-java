package com.estudoeda.walletcore.application.usercase;

public interface WalletUserCase {

    final String TYPE_WALLET_EVENT = "WALLET_CREATED";

    void createWallet(String payload);

}