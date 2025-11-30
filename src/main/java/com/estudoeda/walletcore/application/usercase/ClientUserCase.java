package com.estudoeda.walletcore.application.usercase;

import java.util.UUID;
import com.estudoeda.walletcore.application.domain.model.Client;
import com.estudoeda.walletcore.application.exception.BusinessException;
import com.estudoeda.walletcore.application.gateway.ClientGateway;
import com.estudoeda.walletcore.application.pagination.Page;
import com.estudoeda.walletcore.application.pagination.PageRequest;

public class ClientUserCase {

    private final ClientGateway clientGateway;

    public ClientUserCase(final ClientGateway gateway) {
        this.clientGateway = gateway;
    }

    public boolean existsByName(final String name) {
        return clientGateway.existsByName(name);
    }

    public boolean existsByEmail(final String email) {
        return clientGateway.existsByEmail(email);
    }

    public Page<Client> findAll(final PageRequest pageRequest) {
        return clientGateway.findAll(pageRequest);
    }

    public Client save(final Client client) {
        validarSave(client);
        return clientGateway.save(client);
    }

    public Client update(final UUID id, final Client client) {
        validarUpdate(id, client);
        return clientGateway.update(id, client);
    }

    private void validarSave(final Client client) {
        if (clientGateway.existsByName(client.getName())) {
            getErrorNome(client);
        }
        if (clientGateway.existsByEmail(client.getEmail())) {
            getErrorEmail(client);
        }
    }

    private void validarUpdate(final UUID id, final Client client) {
        if (clientGateway.existsByNameAndIdNot(client.getName(), id)) {
            getErrorNome(client);
        }
        if (clientGateway.existsByEmailAndIdNot(client.getEmail(), id)) {
            getErrorEmail(client);
        }
    }

    private void getErrorNome(final Client client) {
        throw new BusinessException(String.format("Já existe cliente com nome: %s", client.getName()));
    }

    private void getErrorEmail(final Client client) {
        throw new BusinessException(String.format("Já existe cliente com e-mail: %s", client.getEmail()));
    }

    public Client findById(final UUID id) {
        return clientGateway.findById(id);
    }

    public void delete(final UUID id) {
        clientGateway.delete(new Client(id));
    }

}
