package com.estudoeda.walletcore.infrastructure.persistence.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.estudoeda.walletcore.infrastructure.persistence.entity.AccountEntity;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, UUID> {

    AccountEntity findByClientId(UUID clientId);

}
