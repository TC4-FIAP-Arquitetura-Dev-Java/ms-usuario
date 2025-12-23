package com.br.fiap.application.usecase;

import com.br.fiap.domain.model.UserDomain;

public interface UpdateUserUseCase {

    void update(String id, UserDomain userDomain);
}
