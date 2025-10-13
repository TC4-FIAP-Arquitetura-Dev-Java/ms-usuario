package com.br.fiap.application.usecase;

import com.br.fiap.domain.model.UsuarioDomain;

public interface AtualizarUsuarioUseCase {
    void atualizar(String id, UsuarioDomain usuarioDomain);
}
