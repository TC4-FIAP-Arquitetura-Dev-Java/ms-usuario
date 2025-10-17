package com.br.fiap.application.gateways;

import com.br.fiap.domain.model.UsuarioDomain;

import java.util.Optional;

public interface UsuarioGateway {
    Optional<UsuarioDomain> buscarUsuarioPorId(String id);

    Optional<UsuarioDomain> buscarPorUsuario(String username);

    void salvar(UsuarioDomain domain);

    void deletar(UsuarioDomain domain);

    Optional<UsuarioDomain> verificarExistenciaEmailouUsuario(String email, String usuario);
}
