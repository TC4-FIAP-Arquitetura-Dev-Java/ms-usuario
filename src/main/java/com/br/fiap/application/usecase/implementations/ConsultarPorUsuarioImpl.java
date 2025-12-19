package com.br.fiap.application.usecase.implementations;

import com.br.fiap.application.usecase.ConsultarPorUsuarioUseCase;
import com.br.fiap.domain.domainService.UsuarioDomainService;
import com.br.fiap.domain.model.UsuarioDomain;

public class ConsultarPorUsuarioImpl implements ConsultarPorUsuarioUseCase {

   private final UsuarioDomainService  usuarioDomainService;

    public ConsultarPorUsuarioImpl(UsuarioDomainService usuarioDomainService) {
        this.usuarioDomainService = usuarioDomainService;
    }

    @Override
    public UsuarioDomain buscarPorUsuario(String username) {
        return this.usuarioDomainService.buscarPorUsuario(username);
    }
}
