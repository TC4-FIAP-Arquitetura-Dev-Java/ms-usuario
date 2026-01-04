package com.br.fiap.entrypoinsts.controllers.mappers;

import com.br.fiap.domain.enums.RoleEnum;
import com.br.fiap.domain.model.UserDomain;
import com.fiap.ms.userDomain.gen.model.RoleEnumDto;
import com.fiap.ms.userDomain.gen.model.UserRequestDto;
import com.fiap.ms.userDomain.gen.model.UserResponseDto;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserDtoMapperImplTest {

    private final UserDtoMapper mapper = new UserDtoMapperImpl();

    @Test
    void toUserDomain_shouldMapCorrectly() {
        UserRequestDto request = new UserRequestDto();
        request.setUsername("john");
        request.setEmail("john@test.com");
        request.setPassword("123");
        request.setName("John Doe");
        request.setActiveUser(true);
        request.setRoleEnum(com.fiap.ms.userDomain.gen.model.RoleEnumDto.ADMIN);

        UserDomain domain = mapper.toUserDomain(request);

        assertNotNull(domain);
        assertEquals("john", domain.getUsername());
        assertEquals("john@test.com", domain.getEmail());
        assertEquals("123", domain.getPassword());
        assertEquals("John Doe", domain.getName());
        assertTrue(domain.getActiveUser());
        assertEquals(RoleEnum.ADMIN, domain.getRoleEnum());
    }

    @Test
    void toUserResponseDto_shouldMapCorrectly() {
        UserDomain domain = new UserDomain();
        domain.setUsername("john");
        domain.setEmail("john@test.com");
        domain.setPassword("123");
        domain.setName("John Doe");
        domain.setActiveUser(true);
        domain.setRoleEnum(RoleEnum.ADMIN);
        domain.setId("1");

        UserResponseDto dto = mapper.toUserResponseDto(domain);

        assertNotNull(dto);
        assertEquals("john", dto.getUsername());
        assertEquals("john@test.com", dto.getEmail());
        assertEquals("123", dto.getPassword());
        assertEquals("John Doe", dto.getName());
        assertTrue(dto.getActiveUser());
        assertEquals(RoleEnumDto.ADMIN, dto.getRoleEnum());
        assertEquals("1", dto.getId());
    }

    @Test
    void toUserResponseDtos_shouldMapListCorrectly() {
        UserDomain d1 = new UserDomain();
        d1.setUsername("user1");
        d1.setRoleEnum(RoleEnum.USER);

        UserDomain d2 = new UserDomain();
        d2.setUsername("user2");
        d2.setRoleEnum(RoleEnum.ADMIN);

        List<UserResponseDto> dtos = mapper.toUserResponseDtos(List.of(d1, d2));

        assertEquals(2, dtos.size());
        assertEquals("user1", dtos.get(0).getUsername());
        assertEquals(RoleEnumDto.USER, dtos.get(0).getRoleEnum());
        assertEquals("user2", dtos.get(1).getUsername());
        assertEquals(RoleEnumDto.ADMIN, dtos.get(1).getRoleEnum());
    }

    @Test
    void toUserDomain_shouldReturnNull_whenInputIsNull() {
        assertNull(mapper.toUserDomain(null));
    }

    @Test
    void toUserResponseDto_shouldReturnNull_whenInputIsNull() {
        assertNull(mapper.toUserResponseDto(null));
    }

    @Test
    void toUserResponseDtos_shouldReturnNull_whenInputIsNull() {
        assertNull(mapper.toUserResponseDtos(null));
    }
}