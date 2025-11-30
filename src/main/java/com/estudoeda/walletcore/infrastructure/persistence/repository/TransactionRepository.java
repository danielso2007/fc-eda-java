package com.estudoeda.walletcore.infrastructure.persistence.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.estudoeda.walletcore.application.domain.model.Transaction;
import com.estudoeda.walletcore.infrastructure.persistence.entity.TransactionEntity;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity, UUID> {

    Transaction findByAccountFromId(UUID transactionFromId);

    Transaction findByAccountToId(UUID transactionToId);

}
