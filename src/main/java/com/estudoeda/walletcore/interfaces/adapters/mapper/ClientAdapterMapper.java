package com.estudoeda.walletcore.interfaces.adapters.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import com.estudoeda.walletcore.application.domain.model.Client;
import com.estudoeda.walletcore.application.pagination.Page;
import com.estudoeda.walletcore.interfaces.adapters.request.ClientCreateRequestDto;
import com.estudoeda.walletcore.interfaces.adapters.response.ClientResponseDto;

@Mapper(componentModel = "spring")
public interface ClientAdapterMapper {

    @Mapping(source = "name", target = "name")
    @Mapping(source = "email", target = "email")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "accounts", ignore = true)
    Client toModel(ClientCreateRequestDto dto);

    ClientResponseDto toResponse(Client model);

    Page<ClientResponseDto> toResponsePageDto(Page<Client> accounts);
}
