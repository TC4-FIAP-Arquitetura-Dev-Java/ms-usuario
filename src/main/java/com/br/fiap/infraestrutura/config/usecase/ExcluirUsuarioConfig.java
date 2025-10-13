package com.br.fiap.infraestrutura.config.usecase;

import com.br.fiap.application.gateways.UsuarioGateway;
import com.br.fiap.application.usecase.implementations.ExcluirUsuarioUseCaseImpl;
import com.br.fiap.domain.domainService.UsuarioDomainService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ExcluirUsuarioConfig {

    @Bean
    public ExcluirUsuarioUseCaseImpl excluirUsuarioUseCase(UsuarioDomainService usuarioDomainService,
                                                           UsuarioGateway usuarioGateway) {
        return new ExcluirUsuarioUseCaseImpl(usuarioDomainService, usuarioGateway);
    }
}
