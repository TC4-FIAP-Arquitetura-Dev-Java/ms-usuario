package com.br.fiap.entrypoinsts.controllers;

import com.br.fiap.application.dto.UserFilter;
import com.br.fiap.application.usecase.*;
import com.br.fiap.domain.model.UserDomain;
import com.br.fiap.entrypoinsts.controllers.mappers.UserDtoMapper;
import com.fiap.ms.userDomain.UsersApi;
import com.fiap.ms.userDomain.gen.model.PagedUsersResponseDto;
import com.fiap.ms.userDomain.gen.model.UserRequestDto;
import com.fiap.ms.userDomain.gen.model.UserResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    private final UserDtoMapper userDtoMapper;

    public UserController(UpdateUserUseCase updateUserUseCase,
                          ListUsersUseCase listUsersUseCase,
                          GetUserByIdUseCase getUserByIdUseCase,
                          GetByUsernameUseCase getByUsernameUseCase,
                          CreateUserUseCase createUserUseCase,
                          DeleteUserUseCase deleteUserUseCase,
                          UserDtoMapper userDtoMapper) {
        this.updateUserUseCase = updateUserUseCase;
        this.listUsersUseCase = listUsersUseCase;
        this.getUserByIdUseCase = getUserByIdUseCase;
        this.getByUsernameUseCase = getByUsernameUseCase;
        this.createUserUseCase = createUserUseCase;
        this.deleteUserUseCase = deleteUserUseCase;
        this.userDtoMapper = userDtoMapper;
    }

    @Override
    public ResponseEntity<Void> _createUser(UserRequestDto userRequestDto) {
        UserDomain userDomain = userDtoMapper.toUserDomain(userRequestDto);
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
        UserResponseDto userResponseDto = userDtoMapper.toUserResponseDto(userDomain);
        return ResponseEntity.ok(userResponseDto);
    }

    @Override
    public ResponseEntity<UserResponseDto> _getUserByUsername(String username) {
        UserDomain userDomain = getByUsernameUseCase.getByUsername(username);
        UserResponseDto userResponseDto = userDtoMapper.toUserResponseDto(userDomain);
        return ResponseEntity.ok(userResponseDto);
    }

    @Override
    public ResponseEntity<PagedUsersResponseDto> _listUsers(String name, String email, String username, Boolean active, Integer limit, Integer offset) {

        int safeLimit = (limit == null || limit <= 0) ? 10 : limit;
        int safeOffset = (offset == null || offset < 0) ? 0 : offset;

        int page = safeOffset / safeLimit;

        Pageable pageable = PageRequest.of(page, safeLimit);
        UserFilter filter = new UserFilter(name, email, username, active);

        Page<UserDomain> domainPage = listUsersUseCase.findAll(filter, pageable);
        List<UserResponseDto> content = userDtoMapper.toUserResponseDtos(domainPage.getContent());

        PagedUsersResponseDto response = new PagedUsersResponseDto();
        response.setContent(content);
        response.setLimit(safeLimit);
        response.setOffset(safeOffset);
        response.setTotalElements(domainPage.getTotalElements());

        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Void> _updateUser(String id, UserRequestDto userRequestDto) {
        UserDomain userDomain = userDtoMapper.toUserDomain(userRequestDto);
        updateUserUseCase.update(id, userDomain);
        return ResponseEntity.noContent().build();
    }
}