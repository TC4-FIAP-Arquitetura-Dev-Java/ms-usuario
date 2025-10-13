package com.br.fiap.application.usecase.implementations;

import com.br.fiap.domain.domainService.UsuarioDomainService;
import com.br.fiap.domain.model.UsuarioDomain;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.br.fiap.util.mocks.UsuarioDomainMocks.getUsuarioDomain;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ConsultarUsuarioPorIdUseCaseImplTest {

    @InjectMocks
    private ConsultarUsuarioPorIdUseCaseImpl consultarUsuarioPorIdUseCase;

    @Mock
    private UsuarioDomainService usuarioDomainService;

    @Test
    void buscarUsuarioPorId_sucesso() {
        String id = "123";
        UsuarioDomain usuarioDomain = getUsuarioDomain();
        when(usuarioDomainService.buscarUsuarioPorId(id)).thenReturn(usuarioDomain);

        UsuarioDomain domain = consultarUsuarioPorIdUseCase.buscarUsuarioPorId(id);

        assertNotNull(domain);
        verify(usuarioDomainService, times(1)).buscarUsuarioPorId(id);
    }
}
