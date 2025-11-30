package com.estudoeda.walletcore.application.gateway;

import java.util.UUID;
import com.estudoeda.walletcore.application.domain.model.Client;
import com.estudoeda.walletcore.application.pagination.Page;
import com.estudoeda.walletcore.application.pagination.PageRequest;

public interface ClientGateway {
    boolean existsByName(final String name);

    boolean existsByEmail(final String email);

    boolean existsByNameAndIdNot(final String name, final UUID id);

    boolean existsByEmailAndIdNot(final String email, final UUID id);

    Client save(Client client);

    Client update(final UUID id, final Client client);

    Page<Client> findAll(PageRequest pageRequest);

    Client findById(UUID id);

    void delete(Client client);
}
