package com.br.fiap.infraestrutura.config.usecase;

import com.br.fiap.application.usecase.implementations.ConsultarPorUsuarioImpl;
import com.br.fiap.domain.domainService.UsuarioDomainService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConsultarPorUsuarioConfig {

    @Bean
    public ConsultarPorUsuarioImpl consultarPorUsuarioUseCase(UsuarioDomainService usuarioDomainService) {
        return new ConsultarPorUsuarioImpl(usuarioDomainService);
    }
}
