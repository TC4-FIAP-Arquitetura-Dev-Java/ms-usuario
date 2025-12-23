package com.br.fiap.application.usecase;

import com.br.fiap.application.dto.UserFilter;
import com.br.fiap.domain.model.UserDomain;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface ListUsersUseCase {

    Page<UserDomain> findAll(UserFilter filter, Pageable pageable);
}
