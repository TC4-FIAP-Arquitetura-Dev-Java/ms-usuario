package com.br.fiap.infraestrutura.config.domainService;

import com.br.fiap.application.gateways.UserGateway;
import com.br.fiap.domain.domainService.implementations.UserDomainServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UsuarioDomainServiceConfig {

    @Bean
    public UserDomainServiceImpl usuarioDomainService(UserGateway userGateway) {
        return new UserDomainServiceImpl(userGateway);
    }
}
