package com.br.fiap.entrypoinsts.controllers.mappers;

import com.br.fiap.domain.model.UserDomain;
import com.fiap.ms.usuarioDomain.gen.model.UsuarioRequestDto;
import com.fiap.ms.usuarioDomain.gen.model.UsuarioResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface UserDtoMapper {

    UserDtoMapper INSTANCE = Mappers.getMapper(UserDtoMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    UserDomain toUserDomain(UsuarioRequestDto usuarioRequestDto);

    UsuarioResponseDto toUserResponseDto(UserDomain userDomain);

}
