package com.br.fiap.domain.domainService;

import com.br.fiap.domain.model.UserDomain;

public interface UserDomainService {

    UserDomain getById(String id);

    UserDomain getByUsername(String id);

    void checkByEmailOrUsername(String email, String usuario);
}
