package com.br.fiap.entrypoinsts.controllers;

import com.br.fiap.application.usecase.AtualizarUsuarioUseCase;
import com.br.fiap.application.usecase.BuscarUsuariosPaginadoUseCase;
import com.br.fiap.application.usecase.ConsultarUsuarioPorIdUseCase;
import com.br.fiap.application.usecase.CriarUsuarioUseCase;
import com.br.fiap.application.usecase.ExcluirUsuarioUseCase;
import com.fiap.ms.usuarioDomain.gen.model.UsuarioRequestDto;
import com.fiap.ms.usuarioDomain.gen.model.UsuarioResponseDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static com.br.fiap.util.mocks.UsuarioRequestDtoMock.getUsuarioRequestDto;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UsuarioControllerTest {

    @InjectMocks
    private UsuarioController usuarioController;

    @Mock
    private AtualizarUsuarioUseCase atualizarUsuarioUseCase;

    @Mock
    private BuscarUsuariosPaginadoUseCase buscarUsuariosPaginadoUseCase;

    @Mock
    private ConsultarUsuarioPorIdUseCase consultarUsuarioPorIdUseCase;

    @Mock
    private CriarUsuarioUseCase criarUsuarioUseCase;

    @Mock
    private ExcluirUsuarioUseCase excluirUsuarioUseCase;

    @Test
    void atualizarUsuario_sucesso() {
        String id = "123";
        UsuarioRequestDto usuarioRequestDto = getUsuarioRequestDto();
        doNothing().when(atualizarUsuarioUseCase).atualizar(anyString(), any());

        ResponseEntity<Void> result = usuarioController._atualizarUsuario(id, usuarioRequestDto);
        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());
        verify(atualizarUsuarioUseCase).atualizar(anyString(), any());
    }

    @Test
    void consultarUsuarioPorId_sucesso() {
        String id = "123";
        when(consultarUsuarioPorIdUseCase.buscarUsuarioPorId(id)).thenReturn(any());

        ResponseEntity<UsuarioResponseDto> result = usuarioController._consultarUsuarioPorId(id);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        verify(consultarUsuarioPorIdUseCase, times(1)).buscarUsuarioPorId(id);
    }

    @Test
    void criarUsuario_sucesso() {
        UsuarioRequestDto usuarioRequestDto = getUsuarioRequestDto();
        doNothing().when(criarUsuarioUseCase).criar(any());

        ResponseEntity<Void> result = usuarioController._criarUsuario(usuarioRequestDto);
        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        verify(criarUsuarioUseCase, times(1)).criar(any());
    }

    @Test
    void excluirUsuario_sucesso() {
        String id = "123";
        doNothing().when(excluirUsuarioUseCase).deletar(id);

        ResponseEntity<Void> result = usuarioController._excluirUsuario(id);

        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());
        verify(excluirUsuarioUseCase, times(1)).deletar(id);
    }
}
