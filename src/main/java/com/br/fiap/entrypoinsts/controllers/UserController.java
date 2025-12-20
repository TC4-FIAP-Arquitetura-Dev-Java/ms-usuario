package com.br.fiap.entrypoinsts.controllers;

import com.br.fiap.application.dto.UserFilter;
import com.br.fiap.application.usecase.*;
import com.br.fiap.domain.model.UserDomain;
import com.br.fiap.entrypoinsts.controllers.mappers.UserDtoMapper;
import com.br.fiap.entrypoinsts.controllers.mappers.UserFilterMapper;
import com.br.fiap.entrypoinsts.controllers.presenter.UsuarioPresenter;
import com.fiap.ms.usuarioDomain.UsuariosApi;
import com.fiap.ms.usuarioDomain.gen.model.BuscarUsuariosPaginadoDto;
import com.fiap.ms.usuarioDomain.gen.model.UsuarioRequestDto;
import com.fiap.ms.usuarioDomain.gen.model.UsuarioResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/v1")
public class UserController implements UsuariosApi {

    private final UpdateUserUseCase updateUserUseCase;
    private final ListUsersUseCase listUsersUseCase;
    private final GetByUsernameUseCase getByUsernameUseCase;
    private final GetUserByIdUseCase getUserByIdUseCase;
    private final CreateUserUseCase createUserUseCase;
    private final DeleteUserUseCase deleteUserUseCase;

    private final UserFilterMapper userFilterMapper;
    private final UserDtoMapper userDtoMapper;

    public UserController(UpdateUserUseCase updateUserUseCase,
                          ListUsersUseCase listUsersUseCase,
                          GetUserByIdUseCase getUserByIdUseCase,
                          GetByUsernameUseCase getByUsernameUseCase,
                          CreateUserUseCase createUserUseCase,
                          DeleteUserUseCase deleteUserUseCase, UserFilterMapper userFilterMapper, UserDtoMapper userDtoMapper) {
        this.updateUserUseCase = updateUserUseCase;
        this.listUsersUseCase = listUsersUseCase;
        this.getUserByIdUseCase = getUserByIdUseCase;
        this.getByUsernameUseCase = getByUsernameUseCase;
        this.createUserUseCase = createUserUseCase;
        this.deleteUserUseCase = deleteUserUseCase;
        this.userFilterMapper = userFilterMapper;
        this.userDtoMapper = userDtoMapper;
    }

    @Override
    public ResponseEntity<Void> _atualizarUsuario(String id, UsuarioRequestDto usuarioRequestDto) {
        UserDomain userDomain = UsuarioPresenter.toUsuarioDomain(usuarioRequestDto);
        updateUserUseCase.atualizar(id, userDomain);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<BuscarUsuariosPaginadoDto> _buscarUsuariosPaginado(String usuario, Boolean usuarioAtivo, Integer page, Integer size, String sort) {
//        UserFilter filter = userFilterMapper.toFilter()
//        List<UserDomain> domain = listUsersUseCase.findAll(filter);
//        List<BuscarUsuariosPaginadoDto> response = userDtoMapper.toUsuarioResponseDto(domain);
//        return ResponseEntity.ok(response);

        return null;
    }

    @Override
    public ResponseEntity<UsuarioResponseDto> _consultarPorUsuario(String usuario) {
        UserDomain userDomain = getByUsernameUseCase.buscarPorUsuario(usuario);
        UsuarioResponseDto usuarioResponseDto = UsuarioPresenter.toUsuarioRequestDto(userDomain);
        return ResponseEntity.ok(usuarioResponseDto);
    }

    @Override
    public ResponseEntity<UsuarioResponseDto> _consultarUsuarioPorId(String id) {
        UserDomain userDomain = getUserByIdUseCase.buscarUsuarioPorId(id);
        UsuarioResponseDto usuarioResponseDto = UsuarioPresenter.toUsuarioRequestDto(userDomain);
        return ResponseEntity.ok(usuarioResponseDto);
    }

    @Override
    public ResponseEntity<Void> _criarUsuario(UsuarioRequestDto usuarioRequestDto) {
        UserDomain userDomain = UsuarioPresenter.toUsuarioDomain(usuarioRequestDto);
        createUserUseCase.criar(userDomain);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Override
    public ResponseEntity<Void> _excluirUsuario(String id) {
        deleteUserUseCase.deletar(id);
        return ResponseEntity.noContent().build();
    }
}