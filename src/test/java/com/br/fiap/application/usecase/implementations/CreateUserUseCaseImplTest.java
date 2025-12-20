package com.br.fiap.application.usecase.implementations;

import com.br.fiap.application.gateways.UserGateway;
import com.br.fiap.domain.domainService.UserDomainService;
import com.br.fiap.domain.exception.AlreadyExistsException;
import com.br.fiap.domain.model.UserDomain;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.br.fiap.util.mocks.UsuarioDomainMocks.getUsuarioDomain;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CreateUserUseCaseImplTest {

    @InjectMocks
    private CreateUserUseCaseImpl criarUsuarioUseCase;

    @Mock
    private UserDomainService userDomainService;

    @Mock
    private UserGateway userGateway;

    @Test
    void criarUsuario_sucesso() {
        UserDomain userDomain = getUsuarioDomain();
        String email = "marcos.silva@email.com";
        String usuario = "marcos.silva";

        doNothing().when(userDomainService).verificarExistenciaEmailouUsuario(email, usuario);
        doNothing().when(userGateway).save(userDomain);

        criarUsuarioUseCase.criar(userDomain);

        verify(userGateway, times(1)).save(userDomain);
        verify(userDomainService, times(1)).verificarExistenciaEmailouUsuario(email, usuario);
    }

    @Test
    void criarUsuario_erro() {
        UserDomain userDomain = getUsuarioDomain();
        String email = "marcos.silva@email.com";
        String usuario = "marcos.silva";

        doThrow(AlreadyExistsException.class).when(userDomainService).verificarExistenciaEmailouUsuario(email, usuario);

        assertThrows(AlreadyExistsException.class, () -> criarUsuarioUseCase.criar(userDomain));
    }
}
