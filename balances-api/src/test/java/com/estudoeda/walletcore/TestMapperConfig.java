package com.estudoeda.walletcore;

import org.mapstruct.factory.Mappers;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import com.estudoeda.balances.infrastructure.persistence.mapper.BalanceMapper;

@TestConfiguration
public class TestMapperConfig {
    @Bean
    public BalanceMapper accountMapper() {
        return Mappers.getMapper(BalanceMapper.class);
    }

}