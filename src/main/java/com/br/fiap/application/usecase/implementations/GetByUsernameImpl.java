package com.br.fiap.application.usecase.implementations;

import com.br.fiap.application.usecase.GetByUsernameUseCase;
import com.br.fiap.domain.domainService.UserDomainService;
import com.br.fiap.domain.model.UserDomain;

public class GetByUsernameImpl implements GetByUsernameUseCase {

   private final UserDomainService userDomainService;

    public GetByUsernameImpl(UserDomainService userDomainService) {
        this.userDomainService = userDomainService;
    }

    @Override
    public UserDomain buscarPorUsuario(String username) {
        return this.userDomainService.buscarPorUsuario(username);
    }
}
