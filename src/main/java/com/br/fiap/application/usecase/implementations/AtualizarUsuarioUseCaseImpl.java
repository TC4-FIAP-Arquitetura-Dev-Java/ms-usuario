package com.br.fiap.application.usecase.implementations;

import com.br.fiap.application.gateways.UsuarioGateway;
import com.br.fiap.application.usecase.AtualizarUsuarioUseCase;
import com.br.fiap.domain.domainService.UsuarioDomainService;
import com.br.fiap.domain.model.UsuarioDomain;

import static com.br.fiap.domain.rules.ValidarCamposObrigatoriosRule.validarCamposObrigatorios;

public class AtualizarUsuarioUseCaseImpl implements AtualizarUsuarioUseCase {

    private final UsuarioDomainService usuarioDomainService;
    private final UsuarioGateway usuarioGateway;

    public AtualizarUsuarioUseCaseImpl(UsuarioDomainService usuarioDomainService,
                                       UsuarioGateway usuarioGateway) {
        this.usuarioDomainService = usuarioDomainService;
        this.usuarioGateway = usuarioGateway;
    }

    @Override
    public void atualizar(String id, UsuarioDomain usuarioDomain) {
        validarCamposObrigatorios(usuarioDomain);
        UsuarioDomain domain = usuarioDomainService.buscarUsuarioPorId(id);

        domain.setEmail(usuarioDomain.getEmail());
        domain.setNome(usuarioDomain.getNome());
        domain.setUsuarioAtivo(usuarioDomain.getUsuarioAtivo());
        domain.setUsuario(usuarioDomain.getUsuario());

        usuarioGateway.salvar(domain);
    }
}
