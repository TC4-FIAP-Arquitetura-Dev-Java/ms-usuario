package com.br.fiap.entrypoinsts.controllers;

import com.br.fiap.application.usecase.UpdateUserUseCase;
import com.br.fiap.application.usecase.ListUsersUseCase;
import com.br.fiap.application.usecase.GetUserByIdUseCase;
import com.br.fiap.application.usecase.CreateUserUseCase;
import com.br.fiap.application.usecase.DeleteUserUseCase;
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
class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UpdateUserUseCase updateUserUseCase;

    @Mock
    private ListUsersUseCase listUsersUseCase;

    @Mock
    private GetUserByIdUseCase getUserByIdUseCase;

    @Mock
    private CreateUserUseCase createUserUseCase;

    @Mock
    private DeleteUserUseCase deleteUserUseCase;

    @Test
    void atualizarUsuario_sucesso() {
        String id = "123";
        UsuarioRequestDto usuarioRequestDto = getUsuarioRequestDto();
        doNothing().when(updateUserUseCase).atualizar(anyString(), any());

        ResponseEntity<Void> result = userController._atualizarUsuario(id, usuarioRequestDto);
        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());
        verify(updateUserUseCase).atualizar(anyString(), any());
    }

    @Test
    void consultarUsuarioPorId_sucesso() {
        String id = "123";
        when(getUserByIdUseCase.buscarUsuarioPorId(id)).thenReturn(any());

        ResponseEntity<UsuarioResponseDto> result = userController._consultarUsuarioPorId(id);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        verify(getUserByIdUseCase, times(1)).buscarUsuarioPorId(id);
    }

    @Test
    void criarUsuario_sucesso() {
        UsuarioRequestDto usuarioRequestDto = getUsuarioRequestDto();
        doNothing().when(createUserUseCase).criar(any());

        ResponseEntity<Void> result = userController._criarUsuario(usuarioRequestDto);
        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        verify(createUserUseCase, times(1)).criar(any());
    }

    @Test
    void excluirUsuario_sucesso() {
        String id = "123";
        doNothing().when(deleteUserUseCase).deletar(id);

        ResponseEntity<Void> result = userController._excluirUsuario(id);

        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());
        verify(deleteUserUseCase, times(1)).deletar(id);
    }
}
