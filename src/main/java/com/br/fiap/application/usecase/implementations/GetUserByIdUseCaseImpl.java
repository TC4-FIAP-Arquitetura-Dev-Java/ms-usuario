package com.br.fiap.application.usecase.implementations;

import com.br.fiap.application.usecase.GetUserByIdUseCase;
import com.br.fiap.domain.domainService.UserDomainService;
import com.br.fiap.domain.model.UserDomain;

public class GetUserByIdUseCaseImpl implements GetUserByIdUseCase {

    private final UserDomainService userDomainService;

    public GetUserByIdUseCaseImpl(UserDomainService userDomainService) {
        this.userDomainService = userDomainService;
    }

    @Override
    public UserDomain buscarUsuarioPorId(String id) {
        return userDomainService.buscarUsuarioPorId(id);
    }
}
