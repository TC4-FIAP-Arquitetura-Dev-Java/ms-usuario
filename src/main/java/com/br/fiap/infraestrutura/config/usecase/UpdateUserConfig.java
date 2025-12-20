package com.br.fiap.infraestrutura.config.usecase;

import com.br.fiap.application.gateways.SecretKeyGenerator;
import com.br.fiap.application.gateways.UserGateway;
import com.br.fiap.application.usecase.implementations.UpdateUserUseCaseImpl;
import com.br.fiap.domain.domainService.UserDomainService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UpdateUserConfig {

    @Bean
    public UpdateUserUseCaseImpl updateUserUseCase(SecretKeyGenerator secretKeyGenerator,
                                                         UserDomainService userDomainService,
                                                         UserGateway userGateway) {
        return new UpdateUserUseCaseImpl(secretKeyGenerator, userDomainService, userGateway);
    }
}
