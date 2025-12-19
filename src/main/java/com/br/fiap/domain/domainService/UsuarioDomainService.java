package com.br.fiap.domain.domainService;

import com.br.fiap.domain.model.UsuarioDomain;

public interface UsuarioDomainService {

    UsuarioDomain buscarUsuarioPorId(String id);

    UsuarioDomain buscarPorUsuario(String id);

    void verificarExistenciaEmailouUsuario(String email, String usuario);
}
