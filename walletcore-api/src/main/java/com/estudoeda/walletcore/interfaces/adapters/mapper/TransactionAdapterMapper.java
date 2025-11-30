package com.estudoeda.walletcore.interfaces.adapters.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import com.estudoeda.walletcore.application.domain.model.Transaction;
import com.estudoeda.walletcore.application.pagination.Page;
import com.estudoeda.walletcore.interfaces.adapters.request.TransactionCreateRequestDto;
import com.estudoeda.walletcore.interfaces.adapters.response.TransactionResponseDto;

@Mapper(componentModel = "spring")
public interface TransactionAdapterMapper {

    @Mapping(source = "accountFromId", target = "accountFromId")
    @Mapping(source = "accountToId", target = "accountToId")
    @Mapping(source = "amount", target = "amount")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "accountFrom", ignore = true)
    @Mapping(target = "accountTo", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    Transaction toModel(TransactionCreateRequestDto dto);

    TransactionResponseDto toResponse(Transaction model);

    Page<TransactionResponseDto> toResponsePageDto(Page<Transaction> accounts);
}
