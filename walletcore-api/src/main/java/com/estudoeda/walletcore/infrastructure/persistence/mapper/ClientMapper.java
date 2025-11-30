package com.estudoeda.walletcore.infrastructure.persistence.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import com.estudoeda.walletcore.application.domain.model.Client;
import com.estudoeda.walletcore.infrastructure.persistence.entity.ClientEntity;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    ClientEntity toDomain(Client model);

    @Mapping(target = "accounts", ignore = true)
    Client toModel(ClientEntity entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    void updateEntityFromDomain(Client source, @MappingTarget ClientEntity target);
}
