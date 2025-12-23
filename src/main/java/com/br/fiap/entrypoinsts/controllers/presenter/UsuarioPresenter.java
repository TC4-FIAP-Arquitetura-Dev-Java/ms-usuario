package com.br.fiap.entrypoinsts.controllers.presenter;

import com.br.fiap.domain.model.UserDomain;
import com.br.fiap.entrypoinsts.controllers.mappers.UserDtoMapper;
import com.fiap.ms.userDomain.gen.model.UserRequestDto;
import com.fiap.ms.userDomain.gen.model.UserResponseDto;

public class UsuarioPresenter {

    public static UserDomain toUserDomain(UserRequestDto userRequestDto) {
        return UserDtoMapper.INSTANCE.toUserDomain(userRequestDto);
    }

    public static UserResponseDto toUserRequestDto(UserDomain userDomain) {
        return UserDtoMapper.INSTANCE.toUserResponseDto(userDomain);
    }
}
