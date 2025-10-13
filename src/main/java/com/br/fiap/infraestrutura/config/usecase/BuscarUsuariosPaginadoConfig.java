package com.br.fiap.infraestrutura.config.usecase;

import com.br.fiap.application.usecase.implementations.BuscarUsuariosPaginadoUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BuscarUsuariosPaginadoConfig {

    @Bean
    public BuscarUsuariosPaginadoUseCaseImpl buscarUsuariosPaginado() {
        return new BuscarUsuariosPaginadoUseCaseImpl();
    }
}
