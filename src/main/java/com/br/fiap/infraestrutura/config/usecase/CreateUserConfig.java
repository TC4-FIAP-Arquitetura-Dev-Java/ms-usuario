package com.br.fiap.infraestrutura.config.usecase;

import com.br.fiap.application.gateways.SecretKeyGenerator;
import com.br.fiap.application.gateways.UserGateway;
import com.br.fiap.application.usecase.implementations.CreateUserUseCaseImpl;
import com.br.fiap.domain.domainService.UserDomainService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CreateUserConfig {

    @Bean
    public CreateUserUseCaseImpl createUserUseCase(SecretKeyGenerator secretKeyGenerator,
                                                     UserDomainService userDomainService,
                                                     UserGateway userGateway) {
        return new CreateUserUseCaseImpl(secretKeyGenerator, userDomainService, userGateway);
    }
}
