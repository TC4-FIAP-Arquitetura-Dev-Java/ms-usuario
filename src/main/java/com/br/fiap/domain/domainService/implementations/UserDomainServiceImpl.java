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
    public UserDomain buscarUsuarioPorId(String id) {
        return userGateway.buscarUsuarioPorId(id).orElseThrow(
                () -> new UserNotFoundException("O usuário informado não está cadastrado.")
        );
    }

    @Override
    public UserDomain buscarPorUsuario(String usuario) {
        return userGateway.buscarPorUsuario(usuario).orElseThrow(
                () -> new UserNotFoundException("O usuário informado não está cadastrado.")
        );
    }

    @Override
    public void verificarExistenciaEmailouUsuario(String email, String usuario) {
        if(userGateway.verificarExistenciaEmailouUsuario(email, usuario).isPresent()){
            throw new AlreadyExistsException("Usuário ou e-mail já cadastrados!");
        }
    }
}
