package com.br.fiap.application.usecase.implementations;

import com.br.fiap.application.gateways.UsuarioGateway;
import com.br.fiap.application.usecase.ExcluirUsuarioUseCase;
import com.br.fiap.domain.domainService.UsuarioDomainService;
import com.br.fiap.domain.model.UsuarioDomain;

public class ExcluirUsuarioUseCaseImpl implements ExcluirUsuarioUseCase {

    private final UsuarioDomainService usuarioDomainService;
    private final UsuarioGateway usuarioGateway;

    public ExcluirUsuarioUseCaseImpl(UsuarioDomainService usuarioDomainService,
                                     UsuarioGateway usuarioGateway) {
        this.usuarioDomainService = usuarioDomainService;
        this.usuarioGateway = usuarioGateway;
    }

    @Override
    public void deletar(String id) {
        UsuarioDomain domain = usuarioDomainService.buscarUsuarioPorId(id);
        usuarioGateway.deletar(domain);
    }
}
