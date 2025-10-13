package com.br.fiap.util.mocks;

import com.br.fiap.domain.model.UsuarioDomain;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

public class UsuarioDomainMocks {

    public static UsuarioDomain getUsuarioDomain() {
        UsuarioDomain domain = new UsuarioDomain();
        domain.setId("123");
        domain.setUsuario("marcos.silva");
        domain.setNome("Marcos Silva");
        domain.setEmail("marcos.silva@email.com");
        domain.setUsuarioAtivo(true);
        domain.setDataCriacao(OffsetDateTime.of(2024, 10, 10, 12, 0, 0, 0, ZoneOffset.UTC));
        domain.setDataAlteracao(OffsetDateTime.of(2024, 10, 11, 14, 30, 0, 0, ZoneOffset.UTC));
        return domain;
    }
}
