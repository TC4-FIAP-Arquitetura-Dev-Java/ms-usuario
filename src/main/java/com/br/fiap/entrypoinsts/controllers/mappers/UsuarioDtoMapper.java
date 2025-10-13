package com.br.fiap.entrypoinsts.controllers.mappers;

import com.br.fiap.domain.model.UsuarioDomain;
import com.fiap.ms.usuarioDomain.gen.model.BuscarUsuariosPaginadoDto;
import com.fiap.ms.usuarioDomain.gen.model.UsuarioRequestDto;
import com.fiap.ms.usuarioDomain.gen.model.UsuarioResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface UsuarioDtoMapper {

    UsuarioDtoMapper INSTANCE = Mappers.getMapper(UsuarioDtoMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "dataCriacao", ignore = true)
    @Mapping(target = "dataAlteracao", ignore = true)
    UsuarioDomain toUsuarioDomain(UsuarioRequestDto usuarioRequestDto);

    UsuarioResponseDto toUsuarioResponseDto(UsuarioDomain usuarioDomain);

}
