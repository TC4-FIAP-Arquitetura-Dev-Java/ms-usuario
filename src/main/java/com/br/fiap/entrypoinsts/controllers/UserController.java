package com.br.fiap.entrypoinsts.controllers;

import com.br.fiap.application.usecase.*;
import com.br.fiap.domain.model.UserDomain;
import com.br.fiap.entrypoinsts.controllers.mappers.UserDtoMapper;
import com.br.fiap.entrypoinsts.controllers.mappers.UserFilterMapper;
import com.br.fiap.entrypoinsts.controllers.presenter.UsuarioPresenter;
import com.fiap.ms.userDomain.UsersApi;
import com.fiap.ms.userDomain.gen.model.PagedUsersResponseDto;
import com.fiap.ms.userDomain.gen.model.UserRequestDto;
import com.fiap.ms.userDomain.gen.model.UserResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/v1")
public class UserController implements UsersApi {

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
    public ResponseEntity<Void> _createUser(UserRequestDto userRequestDto) {
        UserDomain userDomain = UsuarioPresenter.toUserDomain(userRequestDto);
        createUserUseCase.create(userDomain);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Override
    public ResponseEntity<Void> _deleteUser(String id) {
        deleteUserUseCase.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<UserResponseDto> _getUserById(String id) {
        UserDomain userDomain = getUserByIdUseCase.getById(id);
        UserResponseDto userResponseDto = UsuarioPresenter.toUserRequestDto(userDomain);
        return ResponseEntity.ok(userResponseDto);
    }

    @Override
    public ResponseEntity<UserResponseDto> _getUserByUsername(String username) {
        UserDomain userDomain = getByUsernameUseCase.getByUsername(username);
        UserResponseDto userResponseDto = UsuarioPresenter.toUserRequestDto(userDomain);
        return ResponseEntity.ok(userResponseDto);
    }

    @Override
    public ResponseEntity<PagedUsersResponseDto> _listUsers(String username, Boolean active, Integer limit, Integer offset) {
        return null;
    }

    @Override
    public ResponseEntity<Void> _updateUser(String id, UserRequestDto userRequestDto) {
        UserDomain userDomain = UsuarioPresenter.toUserDomain(userRequestDto);
        updateUserUseCase.update(id, userDomain);
        return ResponseEntity.noContent().build();
    }

//    @Override
//    public ResponseEntity<BuscarUsuariosPaginadoDto> _buscarUsuariosPaginado(String usuario, Boolean usuarioAtivo, Integer page, Integer size, String sort) {
////        UserFilter filter = userFilterMapper.toFilter()
////        List<UserDomain> domain = listUsersUseCase.findAll(filter);
////        List<BuscarUsuariosPaginadoDto> response = userDtoMapper.toUsuarioResponseDto(domain);
////        return ResponseEntity.ok(response);
//
//        return null;
//    }
}