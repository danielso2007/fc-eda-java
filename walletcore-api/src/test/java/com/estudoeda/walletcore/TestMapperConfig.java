package com.estudoeda.walletcore;

import org.mapstruct.factory.Mappers;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import com.estudoeda.walletcore.infrastructure.persistence.mapper.AccountMapper;
import com.estudoeda.walletcore.infrastructure.persistence.mapper.ClientMapper;
import com.estudoeda.walletcore.infrastructure.persistence.mapper.TransactionMapper;
import com.estudoeda.walletcore.interfaces.adapters.mapper.AccountAdapterMapper;
import com.estudoeda.walletcore.interfaces.adapters.mapper.ClientAdapterMapper;
import com.estudoeda.walletcore.interfaces.adapters.mapper.TransactionAdapterMapper;

@TestConfiguration
public class TestMapperConfig {
    @Bean
    public AccountMapper accountMapper() {
        return Mappers.getMapper(AccountMapper.class);
    }

    @Bean
    public ClientMapper clientMapper() {
        return Mappers.getMapper(ClientMapper.class);
    }

    @Bean
    public TransactionMapper transactionMapper() {
        return Mappers.getMapper(TransactionMapper.class);
    }

    @Bean
    public AccountAdapterMapper accountAdapterMapper() {
        return Mappers.getMapper(AccountAdapterMapper.class);
    }

    @Bean
    public ClientAdapterMapper clientAdapterMapper() {
        return Mappers.getMapper(ClientAdapterMapper.class);
    }

    @Bean
    public TransactionAdapterMapper transactionAdapterMapper() {
        return Mappers.getMapper(TransactionAdapterMapper.class);
    }
}