package com.estudoeda.balances.interfaces.adapters.mapper;

import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import com.estudoeda.balances.application.domain.model.Balance;
import com.estudoeda.balances.application.pagination.Page;
import com.estudoeda.balances.interfaces.adapters.request.BalanceRequestDto;
import com.estudoeda.balances.interfaces.adapters.response.BalanceResponseDto;

@Mapper(componentModel = "spring")
public interface BalanceAdapterMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    Balance toModel(BalanceRequestDto dto);

    BalanceResponseDto toResponse(Balance model);

    List<BalanceResponseDto> toResponse(List<Balance> balances);

    Page<BalanceResponseDto> toResponsePageDto(Page<Balance> balances);

}
