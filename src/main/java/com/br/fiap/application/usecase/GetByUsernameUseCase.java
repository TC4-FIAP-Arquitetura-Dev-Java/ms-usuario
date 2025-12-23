package com.br.fiap.application.usecase;

import com.br.fiap.domain.model.UserDomain;

public interface GetByUsernameUseCase {

    UserDomain getByUsername(String username);
}
