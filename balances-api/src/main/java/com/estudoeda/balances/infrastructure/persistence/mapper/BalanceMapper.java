package com.estudoeda.balances.infrastructure.persistence.mapper;

import java.util.List;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import com.estudoeda.balances.application.domain.model.Balance;
import com.estudoeda.balances.infrastructure.persistence.entity.BalanceEntity;

@Mapper(componentModel = "spring")
@Component
public interface BalanceMapper {

    BalanceEntity toDomain(Balance model);

    Balance toModel(BalanceEntity entity);

    List<Balance> toModel(List<BalanceEntity> balances);

}
