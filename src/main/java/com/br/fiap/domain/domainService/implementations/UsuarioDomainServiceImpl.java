package com.br.fiap.domain.domainService.implementations;

import com.br.fiap.application.gateways.UsuarioGateway;
import com.br.fiap.domain.domainService.UsuarioDomainService;
import com.br.fiap.domain.exception.ObjetoJaExisteException;
import com.br.fiap.domain.exception.ObjetoNaoExisteException;
import com.br.fiap.domain.model.UsuarioDomain;

public class UsuarioDomainServiceImpl implements UsuarioDomainService {

    private UsuarioGateway usuarioGateway;

    public UsuarioDomainServiceImpl(UsuarioGateway usuarioGateway) {
        this.usuarioGateway = usuarioGateway;
    }

    @Override
    public UsuarioDomain buscarUsuarioPorId(String id) {
        return usuarioGateway.buscarUsuarioPorId(id).orElseThrow(
                () -> new ObjetoNaoExisteException("O usuário informado não está cadastrado.")
        );
    }

    @Override
    public UsuarioDomain buscarUsuarioPorUsername(String username) {
        return usuarioGateway.buscarUsuarioPorUsername(username).orElseThrow(
                () -> new ObjetoNaoExisteException("O usuário informado não está cadastrado.")
        );
    }

    @Override
    public void verificarExistenciaEmailouUsuario(String email, String usuario) {
        if(usuarioGateway.verificarExistenciaEmailouUsuario(email, usuario).isPresent()){
            throw new ObjetoJaExisteException("Usuário ou e-mail já cadastrados!");
        }
    }
}
