package com.br.fiap.application.usecase.implementations;

import com.br.fiap.application.gateways.SecretKeyGenerator;
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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreateUserUseCaseImplTest {

    @InjectMocks
    private CreateUserUseCaseImpl criarUsuarioUseCase;

    @Mock
    private UserDomainService userDomainService;

    @Mock
    private SecretKeyGenerator secretKeyGenerator;

    @Mock
    private UserGateway userGateway;

    @Test
    void criarUsuario_sucesso() {
        UserDomain userDomain = getUsuarioDomain();
        String email = "marcos.silva@email.com";
        String usuario = "marcos.silva";

        when(secretKeyGenerator.encode(any())).thenReturn("senhaCriptografada");

        doNothing().when(userDomainService).checkByEmailOrUsername(email, usuario);
        doNothing().when(userGateway).save(userDomain);

        criarUsuarioUseCase.create(userDomain);

        verify(userGateway, times(1)).save(userDomain);
        verify(userDomainService, times(1)).checkByEmailOrUsername(email, usuario);
    }

    @Test
    void criarUsuario_erro() {
        UserDomain userDomain = getUsuarioDomain();
        String email = "marcos.silva@email.com";
        String usuario = "marcos.silva";

//        when(secretKeyGenerator.encode(any())).thenReturn("senhaCriptografada");

        doThrow(AlreadyExistsException.class).when(userDomainService).checkByEmailOrUsername(email, usuario);

        assertThrows(AlreadyExistsException.class, () -> criarUsuarioUseCase.create(userDomain));
    }
}
