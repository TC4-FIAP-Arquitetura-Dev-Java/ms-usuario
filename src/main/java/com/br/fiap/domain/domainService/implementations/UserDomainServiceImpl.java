package com.br.fiap.domain.domainService.implementations;

import com.br.fiap.application.gateways.UserGateway;
import com.br.fiap.domain.domainService.UserDomainService;
import com.br.fiap.domain.exception.AlreadyExistsException;
import com.br.fiap.domain.exception.UserNotFoundException;
import com.br.fiap.domain.model.UserDomain;

public class UserDomainServiceImpl implements UserDomainService {

    private UserGateway userGateway;

    public UserDomainServiceImpl(UserGateway userGateway) {
        this.userGateway = userGateway;
    }

    @Override
    public UserDomain getById(String id) {
        return userGateway.getById(id).orElseThrow(
                () -> new UserNotFoundException("O usuário informado não está cadastrado.")
        );
    }

    @Override
    public UserDomain getByUsername(String username) {
        return userGateway.getByUsername(username).orElseThrow(
                () -> new UserNotFoundException("O usuário informado não está cadastrado.")
        );
    }

    @Override
    public void checkByEmailOrUsername(String email, String username) {
        if(userGateway.checkExistenceByEmailOrUser(email, username).isPresent()){
            throw new AlreadyExistsException("Usuário ou e-mail já cadastrados!");
        }
    }
}
