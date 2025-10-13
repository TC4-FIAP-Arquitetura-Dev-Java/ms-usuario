package com.br.fiap.entrypoinsts.controllers.presenter;

import com.br.fiap.domain.model.UsuarioDomain;
import com.br.fiap.entrypoinsts.controllers.mappers.UsuarioDtoMapper;
import com.fiap.ms.usuarioDomain.gen.model.UsuarioRequestDto;
import com.fiap.ms.usuarioDomain.gen.model.UsuarioResponseDto;

public class UsuarioPresenter {

    public static UsuarioDomain toUsuarioDomain(UsuarioRequestDto usuarioRequestDto) {
        return UsuarioDtoMapper.INSTANCE.toUsuarioDomain(usuarioRequestDto);
    }

    public static UsuarioResponseDto toUsuarioRequestDto(UsuarioDomain usuarioDomain) {
        return UsuarioDtoMapper.INSTANCE.toUsuarioResponseDto(usuarioDomain);
    }
}
