package com.br.fiap.application.usecase;

import com.br.fiap.domain.model.UsuarioDomain;

public interface ConsultarUsuarioPorIdUseCase {
    UsuarioDomain buscarUsuarioPorId(String id);
}
