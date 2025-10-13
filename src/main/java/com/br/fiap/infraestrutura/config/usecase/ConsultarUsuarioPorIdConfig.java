package com.br.fiap.infraestrutura.config.usecase;

import com.br.fiap.application.usecase.implementations.ConsultarUsuarioPorIdUseCaseImpl;
import com.br.fiap.domain.domainService.UsuarioDomainService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConsultarUsuarioPorIdConfig {

    @Bean
    public ConsultarUsuarioPorIdUseCaseImpl consultarUsuarioPorIdUseCase(UsuarioDomainService usuarioDomainService) {
        return new ConsultarUsuarioPorIdUseCaseImpl(usuarioDomainService);
    }
}
