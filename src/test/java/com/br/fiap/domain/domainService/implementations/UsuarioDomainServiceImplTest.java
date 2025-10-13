package com.br.fiap.domain.domainService.implementations;

import com.br.fiap.application.gateways.UsuarioGateway;
import com.br.fiap.domain.exception.ObjetoJaExisteException;
import com.br.fiap.domain.exception.ObjetoNaoExisteException;
import com.br.fiap.domain.model.UsuarioDomain;
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
class UsuarioDomainServiceImplTest {

    @InjectMocks
    private UsuarioDomainServiceImpl usuarioDomainServiceImpl;

    @Mock
    private UsuarioGateway usuarioGateway;

    @Test
    void buscarUsuarioPorId_sucesso() {
        String id = "123";
        UsuarioDomain usuarioDomain = getUsuarioDomain();
        when(usuarioGateway.buscarUsuarioPorId(id)).thenReturn(Optional.of(usuarioDomain));

        UsuarioDomain domain = usuarioDomainServiceImpl.buscarUsuarioPorId(id);
        assertNotNull(domain);
        verify(usuarioGateway, times(1)).buscarUsuarioPorId(id);
    }

    @Test
    void buscarUsuarioPorId_erro() {
        String id = "123";
        when(usuarioGateway.buscarUsuarioPorId(id)).thenReturn(Optional.empty());

        assertThrows(ObjetoNaoExisteException.class, () -> usuarioDomainServiceImpl.buscarUsuarioPorId(id));
    }

    @Test
    void verificarExistenciaEmailouUsuario_sucesso() {
        String email = "marcos.silva@email.com";
        String usuario = "marcos.silva";
        when(usuarioGateway.verificarExistenciaEmailouUsuario(email, usuario)).thenReturn(Optional.empty());

        usuarioDomainServiceImpl.verificarExistenciaEmailouUsuario(email, usuario);
        verify(usuarioGateway, times(1)).verificarExistenciaEmailouUsuario(email, usuario);
    }

    @Test
    void verificarExistenciaEmailouUsuario_erro() {
        String email = "marcos.silva@email.com";
        String usuario = "marcos.silva";
        UsuarioDomain usuarioDomain = getUsuarioDomain();

        when(usuarioGateway.verificarExistenciaEmailouUsuario(email, usuario)).thenReturn(Optional.of(usuarioDomain));

        assertThrows(ObjetoJaExisteException.class, () ->
                usuarioDomainServiceImpl.verificarExistenciaEmailouUsuario(email, usuario));
    }
}
