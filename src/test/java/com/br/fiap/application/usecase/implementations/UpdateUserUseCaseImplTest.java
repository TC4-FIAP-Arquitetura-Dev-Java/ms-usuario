package com.br.fiap.application.usecase.implementations;

import com.br.fiap.application.gateways.UserGateway;
import com.br.fiap.domain.domainService.UserDomainService;
import com.br.fiap.domain.exception.UserNotFoundException;
import com.br.fiap.domain.model.UserDomain;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.br.fiap.util.mocks.UsuarioDomainMocks.getUsuarioDomain;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UpdateUserUseCaseImplTest {

    @InjectMocks
    private UpdateUserUseCaseImpl atualizarUsuarioUseCase;

    @Mock
    private UserDomainService userDomainService;

    @Mock
    private UserGateway userGateway;

    @Test
    void atualizarUsuario_sucesso() {
        String id = "123";
        UserDomain domain = getUsuarioDomain();
        when(userDomainService.buscarUsuarioPorId(id)).thenReturn(domain);

        doNothing().when(userGateway).save(domain);

        atualizarUsuarioUseCase.atualizar(id, domain);

        verify(userGateway, times(1)).save(domain);
        verify(userDomainService, times(1)).buscarUsuarioPorId(id);
    }

    @Test
    void atualizarUsuario_erro() {
        String id = "123";
        UserDomain domain = getUsuarioDomain();
        when(userDomainService.buscarUsuarioPorId(id)).thenThrow(UserNotFoundException.class);

        assertThrows(UserNotFoundException.class, () -> atualizarUsuarioUseCase.atualizar(id, domain));
    }
}
