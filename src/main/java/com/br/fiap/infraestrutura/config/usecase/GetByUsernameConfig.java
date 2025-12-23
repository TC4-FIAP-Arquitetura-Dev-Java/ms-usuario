package com.br.fiap.infraestrutura.config.usecase;

import com.br.fiap.application.usecase.implementations.GetByUsernameImpl;
import com.br.fiap.domain.domainService.UserDomainService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GetByUsernameConfig {

    @Bean
    public GetByUsernameImpl getByUsername(UserDomainService userDomainService) {
        return new GetByUsernameImpl(userDomainService);
    }
}
