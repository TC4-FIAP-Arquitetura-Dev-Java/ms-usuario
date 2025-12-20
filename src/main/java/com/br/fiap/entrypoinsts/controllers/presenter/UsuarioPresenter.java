package com.br.fiap.entrypoinsts.controllers.presenter;

import com.br.fiap.domain.model.UserDomain;
import com.br.fiap.entrypoinsts.controllers.mappers.UserDtoMapper;
import com.fiap.ms.usuarioDomain.gen.model.UsuarioRequestDto;
import com.fiap.ms.usuarioDomain.gen.model.UsuarioResponseDto;

public class UsuarioPresenter {

    public static UserDomain toUsuarioDomain(UsuarioRequestDto usuarioRequestDto) {
        return UserDtoMapper.INSTANCE.toUserDomain(usuarioRequestDto);
    }

    public static UsuarioResponseDto toUsuarioRequestDto(UserDomain userDomain) {
        return UserDtoMapper.INSTANCE.toUserResponseDto(userDomain);
    }
}
