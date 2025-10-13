package com.br.fiap.application.usecase.implementations;

import com.br.fiap.application.gateways.UsuarioGateway;
import com.br.fiap.application.usecase.CriarUsuarioUseCase;
import com.br.fiap.domain.domainService.UsuarioDomainService;
import com.br.fiap.domain.model.UsuarioDomain;

import static com.br.fiap.domain.rules.ValidarCamposObrigatoriosRule.validarCamposObrigatorios;

public class CriarUsuarioUseCaseImpl implements CriarUsuarioUseCase {

    private final UsuarioDomainService usuarioDomainService;
    private final UsuarioGateway usuarioGateway;

    public CriarUsuarioUseCaseImpl(UsuarioDomainService usuarioDomainService,
                                       UsuarioGateway usuarioGateway) {
        this.usuarioDomainService = usuarioDomainService;
        this.usuarioGateway = usuarioGateway;
    }

    @Override
    public void criar(UsuarioDomain usuarioDomain) {
        validarCamposObrigatorios(usuarioDomain);
        usuarioDomainService.verificarExistenciaEmailouUsuario(usuarioDomain.getEmail(), usuarioDomain.getUsuario());

        usuarioGateway.salvar(usuarioDomain);
    }
}
