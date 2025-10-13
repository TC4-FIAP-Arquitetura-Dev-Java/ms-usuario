package com.br.fiap.infraestrutura.config.domainService;

import com.br.fiap.application.gateways.UsuarioGateway;
import com.br.fiap.domain.domainService.implementations.UsuarioDomainServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UsuarioDomainServiceConfig {

    @Bean
    public UsuarioDomainServiceImpl usuarioDomainService(UsuarioGateway usuarioGateway) {
        return new UsuarioDomainServiceImpl(usuarioGateway);
    }
}
