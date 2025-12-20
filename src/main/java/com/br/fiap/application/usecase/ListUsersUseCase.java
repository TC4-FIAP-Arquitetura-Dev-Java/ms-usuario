package com.br.fiap.application.usecase;

import com.br.fiap.application.dto.UserFilter;
import com.br.fiap.domain.model.UserDomain;

import java.util.List;

public interface ListUsersUseCase {

    List<UserDomain> findAll(UserFilter filter);
}
