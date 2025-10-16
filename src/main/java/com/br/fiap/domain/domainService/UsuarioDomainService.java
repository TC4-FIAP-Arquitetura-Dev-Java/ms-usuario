package com.br.fiap.domain.domainService;

import com.br.fiap.domain.model.UsuarioDomain;

public interface UsuarioDomainService {

    UsuarioDomain buscarUsuarioPorId(String id);

    UsuarioDomain buscarUsuarioPorUsername(String id);

    void verificarExistenciaEmailouUsuario(String email, String usuario);
}
