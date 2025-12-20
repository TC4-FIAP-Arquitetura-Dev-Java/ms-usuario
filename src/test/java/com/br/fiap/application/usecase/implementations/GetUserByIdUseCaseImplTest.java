package com.br.fiap.application.usecase.implementations;

import com.br.fiap.domain.domainService.UserDomainService;
import com.br.fiap.domain.model.UserDomain;
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
class GetUserByIdUseCaseImplTest {

    @InjectMocks
    private GetUserByIdUseCaseImpl consultarUsuarioPorIdUseCase;

    @Mock
    private UserDomainService userDomainService;

    @Test
    void buscarUsuarioPorId_sucesso() {
        String id = "123";
        UserDomain userDomain = getUsuarioDomain();
        when(userDomainService.buscarUsuarioPorId(id)).thenReturn(userDomain);

        UserDomain domain = consultarUsuarioPorIdUseCase.buscarUsuarioPorId(id);

        assertNotNull(domain);
        verify(userDomainService, times(1)).buscarUsuarioPorId(id);
    }
}
