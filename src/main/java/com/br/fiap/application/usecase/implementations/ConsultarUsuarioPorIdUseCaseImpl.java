package com.br.fiap.application.usecase.implementations;

import com.br.fiap.application.usecase.ConsultarUsuarioPorIdUseCase;
import com.br.fiap.domain.domainService.UsuarioDomainService;
import com.br.fiap.domain.model.UsuarioDomain;

public class ConsultarUsuarioPorIdUseCaseImpl implements ConsultarUsuarioPorIdUseCase {

    private final UsuarioDomainService usuarioDomainService;

    public ConsultarUsuarioPorIdUseCaseImpl(UsuarioDomainService usuarioDomainService) {
        this.usuarioDomainService = usuarioDomainService;
    }

    @Override
    public UsuarioDomain buscarUsuarioPorId(String id) {
        return usuarioDomainService.buscarUsuarioPorId(id);
    }
}
