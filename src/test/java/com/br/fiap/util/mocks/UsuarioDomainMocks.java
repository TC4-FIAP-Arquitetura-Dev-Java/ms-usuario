package com.br.fiap.util.mocks;

import com.br.fiap.application.dto.UserFilter;
import com.br.fiap.domain.model.UserDomain;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

public class UsuarioDomainMocks {

    public static UserDomain getUsuarioDomain() {
        UserDomain domain = new UserDomain();
        domain.setId("123");
        domain.setUsername("marcos.silva");
        domain.setName("Marcos Silva");
        domain.setEmail("marcos.silva@email.com");
        domain.setPassword("asd45fas65d4fas54fa6sd5f4as65d1fsad56f");
        domain.setActiveUser(true);
        domain.setCreatedAt(OffsetDateTime.of(2024, 10, 10, 12, 0, 0, 0, ZoneOffset.UTC));
        domain.setUpdatedAt(OffsetDateTime.of(2024, 10, 11, 14, 30, 0, 0, ZoneOffset.UTC));
        return domain;
    }

    public static UserFilter getUserFilter() {
        return new UserFilter("marcos.silva", "marcos.silva@email.com", "marcos.silva", true);
    }
}
