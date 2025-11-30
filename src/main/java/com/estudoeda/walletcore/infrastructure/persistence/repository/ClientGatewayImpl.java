package com.estudoeda.walletcore.infrastructure.persistence.repository;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import com.estudoeda.walletcore.application.domain.model.Client;
import com.estudoeda.walletcore.application.exception.ClientNotFoundException;
import com.estudoeda.walletcore.application.gateway.ClientGateway;
import com.estudoeda.walletcore.application.pagination.Page;
import com.estudoeda.walletcore.application.pagination.PageRequest;
import com.estudoeda.walletcore.infrastructure.persistence.entity.ClientEntity;
import com.estudoeda.walletcore.infrastructure.persistence.mapper.ClientMapper;

@Repository
public class ClientGatewayImpl implements ClientGateway {

    private static final int CAMPO_DA_TABELA = 0;
    private static final int ORDEM = 1;
    private static final String SEPARAR_POR_VIRGULA = ",";
    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    public ClientGatewayImpl(final ClientRepository clientRepository, final ClientMapper clientMapper) {
        this.clientRepository = clientRepository;
        this.clientMapper = clientMapper;
    }

    @Override
    public Client save(final Client client) {
        return clientMapper.toModel(clientRepository.save(clientMapper.toDomain(client)));
    }

    @Override
    public Client findById(final UUID id) {
        final Optional<ClientEntity> entity = Optional
                .ofNullable(clientRepository.findById(id).orElseThrow(() -> new ClientNotFoundException(id)));
        return clientMapper.toModel(entity.get());
    }

    @Override
    public void delete(final Client client) {
        clientRepository.delete(clientMapper.toDomain(client));
    }

    @Override
    public Page<Client> findAll(final PageRequest pageRequest) {
        final Pageable sp = toSpringPageable(pageRequest);
        final var springPage = clientRepository.findAll(sp);
        final var content = springPage.getContent().stream()
                .map(clientMapper::toModel)
                .toList();
        return new Page<>(content,
                springPage.getNumber(),
                springPage.getSize(),
                springPage.getTotalElements(),
                springPage.getTotalPages());
    }

    private Pageable toSpringPageable(final PageRequest pageRequest) {
        if (Objects.isNull(pageRequest.getSort()) || pageRequest.getSort().isBlank()) {
            return org.springframework.data.domain.PageRequest.of(pageRequest.getPage(), pageRequest.getSize());
        }
        final String[] parts = pageRequest.getSort().split(SEPARAR_POR_VIRGULA);
        final Sort sort = Sort.by(Sort.Direction.fromString(parts[ORDEM].trim()), parts[CAMPO_DA_TABELA].trim());
        return org.springframework.data.domain.PageRequest.of(pageRequest.getPage(), pageRequest.getSize(), sort);
    }

    @Override
    public boolean existsByName(final String name) {
        return clientRepository.existsByName(name);
    }

    @Override
    public boolean existsByEmail(final String email) {
        return clientRepository.existsByEmail(email);
    }

    @Override
    public Client update(UUID id, Client client) {
        ClientEntity existingEntity = clientRepository.findById(id).orElseThrow(() -> new ClientNotFoundException(id));

        clientMapper.updateEntityFromDomain(client, existingEntity);

        clientRepository.save(existingEntity);

        return clientMapper.toModel(existingEntity);
    }

    @Override
    public boolean existsByNameAndIdNot(String name, UUID id) {
        return clientRepository.existsByNameAndIdNot(name, id);
    }

    @Override
    public boolean existsByEmailAndIdNot(String email, UUID id) {
        return clientRepository.existsByEmailAndIdNot(email, id);
    }

}
