package com.br.fiap.infraestrutura.config.usecase;

import com.br.fiap.application.gateways.UserGateway;
import com.br.fiap.application.usecase.implementations.DeleteUserUseCaseImpl;
import com.br.fiap.domain.domainService.UserDomainService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DeleteUserConfig {

    @Bean
    public DeleteUserUseCaseImpl deleteUserUseCase(UserDomainService userDomainService,
                                                       UserGateway userGateway) {
        return new DeleteUserUseCaseImpl(userDomainService, userGateway);
    }
}
