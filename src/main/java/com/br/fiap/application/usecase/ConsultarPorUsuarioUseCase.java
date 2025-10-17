package com.br.fiap.application.usecase;

import com.br.fiap.domain.model.UsuarioDomain;

public interface ConsultarPorUsuarioUseCase {

    UsuarioDomain buscarPorUsuario(String username);
}
