package com.br.fiap.application.usecase.implementations;

import com.br.fiap.application.dto.UserFilter;
import com.br.fiap.application.gateways.UserGateway;
import com.br.fiap.application.usecase.ListUsersUseCase;
import com.br.fiap.domain.model.UserDomain;

import java.util.List;

public class ListUsersUseCaseImpl implements ListUsersUseCase {

    private final UserGateway userGateway;

    public ListUsersUseCaseImpl(UserGateway userGateway) {
        this.userGateway = userGateway;
    }

    @Override
    public List<UserDomain> findAll(UserFilter filter) {
        return userGateway.findAll(filter);
    }
}
