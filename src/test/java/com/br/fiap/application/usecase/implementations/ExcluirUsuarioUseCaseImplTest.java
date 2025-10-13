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
class ExcluirUsuarioUseCaseImplTest {

    @InjectMocks
    private ExcluirUsuarioUseCaseImpl excluirUsuarioUseCase;

    @Mock
    private UsuarioDomainService usuarioDomainService;

    @Mock
    private UsuarioGateway usuarioGateway;

    @Test
    void deletarUsuario_sucesso() {
        UsuarioDomain domain = getUsuarioDomain();
        String id = "123";
        when(usuarioDomainService.buscarUsuarioPorId(id)).thenReturn(domain);
        doNothing().when(usuarioGateway).deletar(domain);

        excluirUsuarioUseCase.deletar(id);

        verify(usuarioGateway, times(1)).deletar(domain);
        verify(usuarioDomainService, times(1)).buscarUsuarioPorId(id);
    }

    @Test
    void deletarUsuario_erro() {
        String id = "123";
        when(usuarioDomainService.buscarUsuarioPorId(id)).thenThrow(ObjetoNaoExisteException.class);

        assertThrows(ObjetoNaoExisteException.class, () -> excluirUsuarioUseCase.deletar(id));
    }
}