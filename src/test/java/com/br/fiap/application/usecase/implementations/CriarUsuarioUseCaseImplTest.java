package com.br.fiap.application.usecase.implementations;

import com.br.fiap.application.gateways.UsuarioGateway;
import com.br.fiap.domain.domainService.UsuarioDomainService;
import com.br.fiap.domain.exception.ObjetoJaExisteException;
import com.br.fiap.domain.model.UsuarioDomain;
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
class CriarUsuarioUseCaseImplTest {

    @InjectMocks
    private CriarUsuarioUseCaseImpl criarUsuarioUseCase;

    @Mock
    private UsuarioDomainService usuarioDomainService;

    @Mock
    private UsuarioGateway usuarioGateway;

    @Test
    void criarUsuario_sucesso() {
        UsuarioDomain usuarioDomain = getUsuarioDomain();
        String email = "marcos.silva@email.com";
        String usuario = "marcos.silva";

        doNothing().when(usuarioDomainService).verificarExistenciaEmailouUsuario(email, usuario);
        doNothing().when(usuarioGateway).salvar(usuarioDomain);

        criarUsuarioUseCase.criar(usuarioDomain);

        verify(usuarioGateway, times(1)).salvar(usuarioDomain);
        verify(usuarioDomainService, times(1)).verificarExistenciaEmailouUsuario(email, usuario);
    }

    @Test
    void criarUsuario_erro() {
        UsuarioDomain usuarioDomain = getUsuarioDomain();
        String email = "marcos.silva@email.com";
        String usuario = "marcos.silva";

        doThrow(ObjetoJaExisteException.class).when(usuarioDomainService).verificarExistenciaEmailouUsuario(email, usuario);

        assertThrows(ObjetoJaExisteException.class, () -> criarUsuarioUseCase.criar(usuarioDomain));
    }
}
