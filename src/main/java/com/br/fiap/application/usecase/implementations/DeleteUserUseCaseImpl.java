package com.br.fiap.application.usecase.implementations;

import com.br.fiap.application.gateways.UserGateway;
import com.br.fiap.application.usecase.DeleteUserUseCase;
import com.br.fiap.domain.domainService.UserDomainService;
import com.br.fiap.domain.model.UserDomain;

public class DeleteUserUseCaseImpl implements DeleteUserUseCase {

    private final UserDomainService userDomainService;
    private final UserGateway userGateway;

    public DeleteUserUseCaseImpl(UserDomainService userDomainService,
                                 UserGateway userGateway) {
        this.userDomainService = userDomainService;
        this.userGateway = userGateway;
    }

    @Override
    public void deletar(String id) {
        UserDomain domain = userDomainService.buscarUsuarioPorId(id);
        userGateway.deletar(domain);
    }
}
