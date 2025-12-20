package com.br.fiap.application.gateways;

import com.br.fiap.application.dto.UserFilter;
import com.br.fiap.domain.model.UserDomain;

import java.util.List;
import java.util.Optional;

public interface UserGateway {

    Optional<UserDomain> getById(String id);

    Optional<UserDomain> getByUsername(String username);

    List<UserDomain> findAll(UserFilter filter);

    void save(UserDomain domain);

    void delete(UserDomain domain);

    Optional<UserDomain> checkExistenceByEmailOrUser(String email, String user);
}
