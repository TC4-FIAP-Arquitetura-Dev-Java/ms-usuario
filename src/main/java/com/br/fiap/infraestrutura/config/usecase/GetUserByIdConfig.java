package com.br.fiap.infraestrutura.config.usecase;

import com.br.fiap.application.usecase.implementations.GetUserByIdUseCaseImpl;
import com.br.fiap.domain.domainService.UserDomainService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GetUserByIdConfig {

    @Bean
    public GetUserByIdUseCaseImpl getUserByIdUseCase(UserDomainService userDomainService) {
        return new GetUserByIdUseCaseImpl(userDomainService);
    }
}
