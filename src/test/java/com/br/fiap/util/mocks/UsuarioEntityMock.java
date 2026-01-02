package com.br.fiap.util.mocks;

import com.br.fiap.domain.enums.RoleEnum;
import com.br.fiap.infraestrutura.database.entities.UserEntity;

import java.time.Instant;

public class UsuarioEntityMock {

    public static UserEntity getUsuarioEntity() {
        UserEntity entity = new UserEntity();
        entity.setId(1L);
        entity.setUsername("marcos.silva");
        entity.setName("Marcos Silva");
        entity.setEmail("marcos.silva@email.com");
        entity.setPassword("asd45fas65d4fas54fa6sd5f4as65d1fsad56f");
        entity.setActiveUser(true);
        entity.setRoleEnum(RoleEnum.USER);
        entity.setCreatedAt(Instant.parse("2024-10-10T12:00:00Z"));
        entity.setUpdatedAt(Instant.parse("2024-10-11T14:30:00Z"));
        return entity;
    }
}

