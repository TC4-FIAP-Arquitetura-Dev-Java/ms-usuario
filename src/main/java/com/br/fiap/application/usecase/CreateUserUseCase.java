package com.br.fiap.application.usecase;

import com.br.fiap.domain.model.UserDomain;

public interface CreateUserUseCase {
    void create(UserDomain userDomain);
}
