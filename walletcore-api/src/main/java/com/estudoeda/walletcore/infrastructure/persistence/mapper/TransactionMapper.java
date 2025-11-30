package com.estudoeda.walletcore.infrastructure.persistence.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import com.estudoeda.walletcore.application.domain.model.Transaction;
import com.estudoeda.walletcore.infrastructure.persistence.entity.TransactionEntity;

@Mapper(componentModel = "spring")
public interface TransactionMapper {

    TransactionEntity toDomain(Transaction model);

    @Mapping(target = "accountFrom", ignore = true)
    @Mapping(target = "accountTo", ignore = true)
    Transaction toModel(TransactionEntity entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    void updateEntityFromDomain(Transaction source, @MappingTarget TransactionEntity target);
}
