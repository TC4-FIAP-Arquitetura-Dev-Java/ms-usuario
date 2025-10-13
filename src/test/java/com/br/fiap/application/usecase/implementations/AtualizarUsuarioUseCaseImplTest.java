package com.br.fiap.application.usecase.implementations;

import com.br.fiap.application.gateways.UsuarioGateway;
import com.br.fiap.domain.domainService.UsuarioDomainService;
import com.br.fiap.domain.exception.ObjetoNaoExisteException;
import com.br.fiap.domain.model.UsuarioDomain;
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
class AtualizarUsuarioUseCaseImplTest {

    @InjectMocks
    private AtualizarUsuarioUseCaseImpl atualizarUsuarioUseCase;

    @Mock
    private UsuarioDomainService usuarioDomainService;

    @Mock
    private UsuarioGateway usuarioGateway;

    @Test
    void atualizarUsuario_sucesso() {
        String id = "123";
        UsuarioDomain domain = getUsuarioDomain();
        when(usuarioDomainService.buscarUsuarioPorId(id)).thenReturn(domain);

        doNothing().when(usuarioGateway).salvar(domain);

        atualizarUsuarioUseCase.atualizar(id, domain);

        verify(usuarioGateway, times(1)).salvar(domain);
        verify(usuarioDomainService, times(1)).buscarUsuarioPorId(id);
    }

    @Test
    void atualizarUsuario_erro() {
        String id = "123";
        UsuarioDomain domain = getUsuarioDomain();
        when(usuarioDomainService.buscarUsuarioPorId(id)).thenThrow(ObjetoNaoExisteException.class);

        assertThrows(ObjetoNaoExisteException.class, () -> atualizarUsuarioUseCase.atualizar(id, domain));
    }
}
