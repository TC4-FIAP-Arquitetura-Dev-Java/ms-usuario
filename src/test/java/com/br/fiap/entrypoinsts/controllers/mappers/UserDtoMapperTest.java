package com.br.fiap.entrypoinsts.controllers.mappers;

import com.br.fiap.domain.enums.RoleEnum;
import com.br.fiap.domain.model.UserDomain;
import com.fiap.ms.userDomain.gen.model.RoleEnumDto;
import com.fiap.ms.userDomain.gen.model.UserRequestDto;
import com.fiap.ms.userDomain.gen.model.UserResponseDto;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserDtoMapperTest {

    private final UserDtoMapper mapper = UserDtoMapper.INSTANCE;

    @Test
    void toUserDomain_shouldMapCorrectly() {
        UserRequestDto requestDto = new UserRequestDto();
        requestDto.setUsername("john");
        requestDto.setEmail("john@test.com");
        requestDto.setPassword("123456");
        requestDto.setRoleEnum(RoleEnumDto.ADMIN);

        UserDomain domain = mapper.toUserDomain(requestDto);

        assertNotNull(domain);
        assertEquals("john", domain.getUsername());
        assertEquals("john@test.com", domain.getEmail());
        assertEquals("123456", domain.getPassword());
        assertEquals(RoleEnum.ADMIN, domain.getRoleEnum());
        assertNull(domain.getId());
        assertNull(domain.getCreatedAt());
        assertNull(domain.getUpdatedAt());
    }

    @Test
    void toUserResponseDto_shouldMapCorrectly() {
        UserDomain domain = new UserDomain();
        domain.setUsername("john");
        domain.setEmail("john@test.com");
        domain.setRoleEnum(RoleEnum.ADMIN);

        UserResponseDto responseDto = mapper.toUserResponseDto(domain);

        assertNotNull(responseDto);
        assertEquals("john", responseDto.getUsername());
        assertEquals("john@test.com", responseDto.getEmail());
        assertEquals(RoleEnumDto.ADMIN, responseDto.getRoleEnum());
    }

    @Test
    void toUserResponseDtos_shouldMapListCorrectly() {
        UserDomain domain1 = new UserDomain();
        domain1.setUsername("user1");
        domain1.setRoleEnum(RoleEnum.USER);

        UserDomain domain2 = new UserDomain();
        domain2.setUsername("user2");
        domain2.setRoleEnum(RoleEnum.ADMIN);

        List<UserResponseDto> dtos = mapper.toUserResponseDtos(List.of(domain1, domain2));

        assertEquals(2, dtos.size());
        assertEquals("user1", dtos.get(0).getUsername());
        assertEquals(RoleEnumDto.USER, dtos.get(0).getRoleEnum());
        assertEquals("user2", dtos.get(1).getUsername());
        assertEquals(RoleEnumDto.ADMIN, dtos.get(1).getRoleEnum());
    }

    @Test
    void mapRole_shouldReturnNull_whenInputIsNull() {
        assertNull(mapper.mapRole(null));
    }

    @Test
    void mapRoleDto_shouldReturnNull_whenInputIsNull() {
        assertNull(mapper.mapRoleDto(null));
    }

    @Test
    void mapRole_shouldConvertStringToEnum() {
        assertEquals(RoleEnum.ADMIN, mapper.mapRole("admin"));
        assertEquals(RoleEnum.USER, mapper.mapRole("USER"));
    }

    @Test
    void mapRoleDto_shouldConvertStringToDtoEnum() {
        assertEquals(RoleEnumDto.ADMIN, mapper.mapRoleDto("admin"));
        assertEquals(RoleEnumDto.USER, mapper.mapRoleDto("USER"));
    }
}