package com.estudoeda.balances.infrastructure.persistence.repository;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.estudoeda.balances.infrastructure.persistence.entity.BalanceEntity;

@Repository
public interface BalanceRepository extends JpaRepository<BalanceEntity, UUID> {

    List<BalanceEntity> findByTransactionId(final UUID transactionId);

    List<BalanceEntity> findByAccountId(final UUID accountId);

}
