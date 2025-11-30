package com.estudoeda.walletcore.infrastructure.persistence.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;
import com.estudoeda.walletcore.application.domain.model.Account;
import com.estudoeda.walletcore.infrastructure.persistence.entity.AccountEntity;

@Mapper(componentModel = "spring")
@Component
public interface AccountMapper {

    AccountEntity toDomain(Account model);

    @Mapping(target = "client", ignore = true)
    Account toModel(AccountEntity entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    void updateEntityFromDomain(Account source, @MappingTarget AccountEntity target);
}
