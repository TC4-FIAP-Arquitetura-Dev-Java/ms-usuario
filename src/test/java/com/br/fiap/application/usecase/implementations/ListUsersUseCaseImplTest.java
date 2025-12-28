package com.br.fiap.application.usecase.implementations;

import com.br.fiap.application.dto.UserFilter;
import com.br.fiap.application.gateways.UserGateway;
import com.br.fiap.domain.model.UserDomain;
import com.br.fiap.util.mocks.UsuarioDomainMocks;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ListUsersUseCaseImplTest {

    @Mock
    private UserGateway userGateway;

    @InjectMocks
    private ListUsersUseCaseImpl listUsersUseCase;

    @Test
    void deveRetornarUsuariosQuandoExistiremNaBase() {
        // Arrange
        UserFilter filter = UsuarioDomainMocks.getUserFilter();
        Pageable pageable = PageRequest.of(0, 10);
        List<UserDomain> usuarios = List.of(UsuarioDomainMocks.getUsuarioDomain());
        Page<UserDomain> page = new PageImpl<>(usuarios, pageable, usuarios.size());

        when(userGateway.findWithFilter(filter, pageable)).thenReturn(page);

        // Act
        Page<UserDomain> result = listUsersUseCase.findAll(filter, pageable);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.getContent().size());
        assertEquals("marcos.silva", result.getContent().get(0).getUsername());

        verify(userGateway, times(1)).findWithFilter(filter, pageable);
    }

    @Test
    void deveRetornarPaginaVaziaQuandoNaoHouverUsuarios() {
        // Arrange
        UserFilter filter = UsuarioDomainMocks.getUserFilter();
        Pageable pageable = PageRequest.of(0, 10);
        Page<UserDomain> emptyPage = Page.empty(pageable);

        when(userGateway.findWithFilter(filter, pageable)).thenReturn(emptyPage);

        // Act
        Page<UserDomain> result = listUsersUseCase.findAll(filter, pageable);

        // Assert
        assertTrue(result.isEmpty());
        verify(userGateway, times(1)).findWithFilter(filter, pageable);
    }
}