package com.br.fiap.infraestrutura.config.usecase;

import com.br.fiap.application.gateways.UsuarioGateway;
import com.br.fiap.application.usecase.implementations.CriarUsuarioUseCaseImpl;
import com.br.fiap.domain.domainService.UsuarioDomainService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CriarUsuarioConfig {

    @Bean
    public CriarUsuarioUseCaseImpl criarUsuarioUseCase(UsuarioDomainService usuarioDomainService,
                                                       UsuarioGateway usuarioGateway) {
        return new CriarUsuarioUseCaseImpl(usuarioDomainService, usuarioGateway);
    }
}
