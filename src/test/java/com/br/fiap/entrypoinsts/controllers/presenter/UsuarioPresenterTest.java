package com.br.fiap.entrypoinsts.controllers.presenter;

import com.br.fiap.domain.model.UserDomain;
import com.fiap.ms.userDomain.gen.model.UserRequestDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UsuarioPresenterTest {

    @Test
    void deveConverterUserRequestDtoParaUserDomain() {
        UserRequestDto request = new UserRequestDto();
        request.setUsername("marcos.silva");
        request.setName("Marcos Silva");
        request.setEmail("marcos@email.com");
        request.setPassword("123456");

        var domain = UsuarioPresenter.toUserDomain(request);

        assertNotNull(domain);
        assertEquals(request.getUsername(), domain.getUsername());
        assertEquals(request.getName(), domain.getName());
        assertEquals(request.getEmail(), domain.getEmail());
        assertEquals(request.getPassword(), domain.getPassword());
    }

    @Test
    void deveConverterUserDomainParaUserResponseDto() {
        UserDomain domain = new UserDomain();
        domain.setId("123");
        domain.setUsername("marcos.silva");
        domain.setName("Marcos Silva");
        domain.setEmail("marcos@email.com");
        domain.setActiveUser(true);

        var response = UsuarioPresenter.toUserResponseDto(domain);

        assertNotNull(response);
        assertEquals(domain.getId(), response.getId());
        assertEquals(domain.getUsername(), response.getUsername());
        assertEquals(domain.getName(), response.getName());
        assertEquals(domain.getEmail(), response.getEmail());
        assertEquals(domain.getActiveUser(), response.getActiveUser());
    }
}