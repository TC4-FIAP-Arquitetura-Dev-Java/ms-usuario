package com.br.fiap.application.usecase.implementations;

import com.br.fiap.application.usecase.ConsultarUsuarioPorUsernameUseCase;
import com.br.fiap.domain.domainService.UsuarioDomainService;
import com.br.fiap.domain.model.UsuarioDomain;

public class ConsultarUsuarioPorUserNameImpl implements ConsultarUsuarioPorUsernameUseCase {

   private final UsuarioDomainService  usuarioDomainService;

    public ConsultarUsuarioPorUserNameImpl(UsuarioDomainService usuarioDomainService) {
        this.usuarioDomainService = usuarioDomainService;
    }

    @Override
    public UsuarioDomain buscarUsuarioPorUsername(String username) {
        return this.usuarioDomainService.buscarUsuarioPorUsername(username);
    }
}
