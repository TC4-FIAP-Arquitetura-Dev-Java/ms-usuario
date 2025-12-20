package com.br.fiap.application.usecase.implementations;

import com.br.fiap.application.gateways.SecretKeyGenerator;
import com.br.fiap.application.gateways.UserGateway;
import com.br.fiap.application.usecase.UpdateUserUseCase;
import com.br.fiap.domain.domainService.UserDomainService;
import com.br.fiap.domain.model.UserDomain;

import static com.br.fiap.domain.rules.ValidarCamposObrigatoriosRule.validarCamposObrigatorios;

public class UpdateUserUseCaseImpl implements UpdateUserUseCase {

    private final SecretKeyGenerator secretKeyGenerator;
    private final UserDomainService userDomainService;
    private final UserGateway userGateway;

    public UpdateUserUseCaseImpl(SecretKeyGenerator secretKeyGenerator,
                                 UserDomainService userDomainService,
                                 UserGateway userGateway) {
        this.secretKeyGenerator = secretKeyGenerator;
        this.userDomainService = userDomainService;
        this.userGateway = userGateway;
    }

    @Override
    public void atualizar(String id, UserDomain userDomain) {
        validarCamposObrigatorios(userDomain);
        UserDomain domain = userDomainService.buscarUsuarioPorId(id);

        domain.setEmail(userDomain.getEmail());
        domain.setName(userDomain.getName());
        domain.setActiveUser(userDomain.getActiveUser());
        domain.setUsername(userDomain.getUsername().toLowerCase());
        domain.setPassword(secretKeyGenerator.encode(userDomain.getPassword()));

        userGateway.save(domain);
    }
}
