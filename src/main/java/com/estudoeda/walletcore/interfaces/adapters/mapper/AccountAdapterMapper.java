package com.estudoeda.walletcore.interfaces.adapters.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import com.estudoeda.walletcore.application.domain.model.Account;
import com.estudoeda.walletcore.application.pagination.Page;
import com.estudoeda.walletcore.interfaces.adapters.request.AccountCreateRequestDto;
import com.estudoeda.walletcore.interfaces.adapters.response.AccountResponseDto;

@Mapper(componentModel = "spring")
public interface AccountAdapterMapper {
    public static final String CLIENT_ID = "clientId";
    
    @Mapping(source = CLIENT_ID, target = CLIENT_ID)
    @Mapping(target = "client", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "balance", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Account toModel(AccountCreateRequestDto dto);

    @Mapping(source = "id", target = "id")
    @Mapping(source = CLIENT_ID, target = CLIENT_ID)
    @Mapping(source = "balance", target = "balance")
    AccountResponseDto toResponse(Account model);

    Page<AccountResponseDto> toResponsePageDto(Page<Account> accounts);
}
