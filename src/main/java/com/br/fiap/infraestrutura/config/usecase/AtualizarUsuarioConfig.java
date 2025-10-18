package com.br.fiap.infraestrutura.config.usecase;

import com.br.fiap.application.gateways.SecretKeyGenerator;
import com.br.fiap.application.gateways.UsuarioGateway;
import com.br.fiap.application.usecase.implementations.AtualizarUsuarioUseCaseImpl;
import com.br.fiap.domain.domainService.UsuarioDomainService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AtualizarUsuarioConfig {

    @Bean
    public AtualizarUsuarioUseCaseImpl atualizarUsuarioUseCase(SecretKeyGenerator secretKeyGenerator,
                                                               UsuarioDomainService usuarioDomainService,
                                                               UsuarioGateway usuarioGateway) {
        return new AtualizarUsuarioUseCaseImpl(secretKeyGenerator, usuarioDomainService, usuarioGateway);
    }
}
