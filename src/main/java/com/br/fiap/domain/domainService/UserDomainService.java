package com.br.fiap.domain.domainService;

import com.br.fiap.domain.model.UserDomain;

public interface UserDomainService {

    UserDomain buscarUsuarioPorId(String id);

    UserDomain buscarPorUsuario(String id);

    void verificarExistenciaEmailouUsuario(String email, String usuario);
}
