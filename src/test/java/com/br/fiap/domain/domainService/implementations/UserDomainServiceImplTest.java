package com.br.fiap.domain.domainService.implementations;

import com.br.fiap.application.gateways.UserGateway;
import com.br.fiap.domain.exception.AlreadyExistsException;
import com.br.fiap.domain.exception.UserNotFoundException;
import com.br.fiap.domain.model.UserDomain;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.br.fiap.util.mocks.UsuarioDomainMocks.getUsuarioDomain;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserDomainServiceImplTest {

    @InjectMocks
    private UserDomainServiceImpl usuarioDomainServiceImpl;

    @Mock
    private UserGateway userGateway;

    @Test
    void buscarUsuarioPorId_sucesso() {
        String id = "123";
        UserDomain userDomain = getUsuarioDomain();
        when(userGateway.buscarUsuarioPorId(id)).thenReturn(Optional.of(userDomain));

        UserDomain domain = usuarioDomainServiceImpl.buscarUsuarioPorId(id);
        assertNotNull(domain);
        verify(userGateway, times(1)).buscarUsuarioPorId(id);
    }

    @Test
    void buscarUsuarioPorId_erro() {
        String id = "123";
        when(userGateway.buscarUsuarioPorId(id)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> usuarioDomainServiceImpl.buscarUsuarioPorId(id));
    }

    @Test
    void verificarExistenciaEmailouUsuario_sucesso() {
        String email = "marcos.silva@email.com";
        String usuario = "marcos.silva";
        when(userGateway.verificarExistenciaEmailouUsuario(email, usuario)).thenReturn(Optional.empty());

        usuarioDomainServiceImpl.verificarExistenciaEmailouUsuario(email, usuario);
        verify(userGateway, times(1)).verificarExistenciaEmailouUsuario(email, usuario);
    }

    @Test
    void verificarExistenciaEmailouUsuario_erro() {
        String email = "marcos.silva@email.com";
        String usuario = "marcos.silva";
        UserDomain userDomain = getUsuarioDomain();

        when(userGateway.verificarExistenciaEmailouUsuario(email, usuario)).thenReturn(Optional.of(userDomain));

        assertThrows(AlreadyExistsException.class, () ->
                usuarioDomainServiceImpl.verificarExistenciaEmailouUsuario(email, usuario));
    }
}
