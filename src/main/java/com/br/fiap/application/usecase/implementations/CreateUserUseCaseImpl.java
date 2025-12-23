package com.br.fiap.application.usecase.implementations;

import com.br.fiap.application.gateways.SecretKeyGenerator;
import com.br.fiap.application.gateways.UserGateway;
import com.br.fiap.application.usecase.CreateUserUseCase;
import com.br.fiap.domain.domainService.UserDomainService;
import com.br.fiap.domain.model.UserDomain;

import static com.br.fiap.domain.rules.ValidarCamposObrigatoriosRule.validarCamposObrigatorios;

public class CreateUserUseCaseImpl implements CreateUserUseCase {

    private final SecretKeyGenerator  secretKeyGenerator;
    private final UserDomainService userDomainService;
    private final UserGateway userGateway;

    public CreateUserUseCaseImpl(SecretKeyGenerator secretKeyGenerator,
                                 UserDomainService userDomainService,
                                 UserGateway userGateway) {
        this.secretKeyGenerator = secretKeyGenerator;
        this.userDomainService = userDomainService;
        this.userGateway = userGateway;
    }

    @Override
    public void create(UserDomain userDomain) {
        validarCamposObrigatorios(userDomain);
        userDomainService.checkByEmailOrUsername(userDomain.getEmail(), userDomain.getUsername());
        userDomain.setUsername(userDomain.getUsername().toLowerCase());
        userDomain.setPassword(secretKeyGenerator.encode(userDomain.getPassword()));
        userGateway.save(userDomain);
    }
}
