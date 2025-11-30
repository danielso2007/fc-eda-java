package com.estudoeda.walletcore.infrastructure.persistence.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.estudoeda.walletcore.infrastructure.persistence.entity.ClientEntity;

@Repository
public interface ClientRepository extends JpaRepository<ClientEntity, UUID> {

    boolean existsByName(final String name);

    boolean existsByEmail(final String email);

    boolean existsByNameAndIdNot(final String name, final UUID id);

    boolean existsByEmailAndIdNot(final String email, final UUID id);

}
