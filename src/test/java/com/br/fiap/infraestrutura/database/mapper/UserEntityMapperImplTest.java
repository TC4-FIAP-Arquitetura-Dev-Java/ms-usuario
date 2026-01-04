package com.br.fiap.infraestrutura.database.mapper;

import com.br.fiap.domain.model.UserDomain;
import com.br.fiap.infraestrutura.database.entities.UserEntity;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import static org.junit.jupiter.api.Assertions.*;

class UserEntityMapperImplTest {

    private final UserEntityMapperImpl mapper = new UserEntityMapperImpl();

    @Test
    void toDomain_shouldMapAllFields() {
        UserEntity entity = new UserEntity();
        entity.setId(10L);
        entity.setName("John");
        entity.setUsername("johnny");
        entity.setPassword("pass");
        entity.setEmail("john@email.com");
        entity.setActiveUser(true);
        entity.setRoleEnum(null);
        entity.setCreatedAt(Instant.parse("2026-01-01T10:00:00Z"));
        entity.setUpdatedAt(Instant.parse("2026-01-02T12:00:00Z"));

        UserDomain domain = mapper.toDomain(entity);

        assertNotNull(domain);
        assertEquals("10", domain.getId());
        assertEquals("John", domain.getName());
        assertEquals("johnny", domain.getUsername());
        assertEquals("pass", domain.getPassword());
        assertEquals("john@email.com", domain.getEmail());
        assertTrue(domain.getActiveUser());
        assertNull(domain.getRoleEnum());
        assertEquals(OffsetDateTime.ofInstant(entity.getCreatedAt(), ZoneOffset.UTC), domain.getCreatedAt());
        assertEquals(OffsetDateTime.ofInstant(entity.getUpdatedAt(), ZoneOffset.UTC), domain.getUpdatedAt());
    }

    @Test
    void toDomain_shouldReturnNull_whenEntityIsNull() {
        assertNull(mapper.toDomain(null));
    }

    @Test
    void toEntity_shouldMapAllFields() {
        UserDomain domain = new UserDomain();
        domain.setId("20");
        domain.setName("Alice");
        domain.setUsername("alice123");
        domain.setPassword("secret");
        domain.setEmail("alice@email.com");
        domain.setActiveUser(false);
        domain.setRoleEnum(null);
        domain.setCreatedAt(OffsetDateTime.parse("2026-01-01T10:00:00Z"));
        domain.setUpdatedAt(OffsetDateTime.parse("2026-01-02T12:00:00Z"));

        UserEntity entity = mapper.toEntity(domain);

        assertNotNull(entity);
        assertEquals(20L, entity.getId());
        assertEquals("Alice", entity.getName());
        assertEquals("alice123", entity.getUsername());
        assertEquals("secret", entity.getPassword());
        assertEquals("alice@email.com", entity.getEmail());
        assertFalse(entity.getActiveUser());
        assertNull(entity.getRoleEnum());
        assertEquals(domain.getCreatedAt().toInstant(), entity.getCreatedAt());
        assertEquals(domain.getUpdatedAt().toInstant(), entity.getUpdatedAt());
    }

    @Test
    void toEntity_shouldReturnNull_whenDomainIsNull() {
        assertNull(mapper.toEntity(null));
    }

    @Test
    void toEntity_shouldHandleEmptyId() {
        UserDomain domain = new UserDomain();
        domain.setId("");
        UserEntity entity = mapper.toEntity(domain);
        assertNull(entity.getId());
    }
}