package com.br.fiap.infraestrutura.config.usecase;

import com.br.fiap.application.gateways.UserGateway;
import com.br.fiap.application.usecase.implementations.ListUsersUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ListUsersConfig {

    @Bean
    public ListUsersUseCaseImpl listUsersUseCase(UserGateway userGateway) {
        return new ListUsersUseCaseImpl(userGateway);
    }
}
