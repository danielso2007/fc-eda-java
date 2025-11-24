package com.estudoeda.walletcore.interfaces.controller.impl;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.estudoeda.walletcore.application.usercase.WalletUserCase;
import com.estudoeda.walletcore.interfaces.controller.WalletController;
import io.micrometer.core.annotation.Timed;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/wallets")
public class WalletControllerImpl implements WalletController {

    private final WalletUserCase service;

    public WalletControllerImpl(WalletUserCase service) {
        this.service = service;
    }

    @Override
    @Timed(value = "http.server.requests", extraTags = { "uri",
        "/api/wallets" }, description = "Create Wallet", histogram = true)
    @PostMapping(produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<Void> create(@RequestBody String payload) {
        service.createWallet(payload);
        return ResponseEntity.accepted().build();
    }
}
