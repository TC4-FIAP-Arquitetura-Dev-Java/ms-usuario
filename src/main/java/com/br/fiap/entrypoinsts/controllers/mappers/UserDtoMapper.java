package com.br.fiap.entrypoinsts.controllers.mappers;

import com.br.fiap.domain.model.UserDomain;
import com.fiap.ms.userDomain.gen.model.UserRequestDto;
import com.fiap.ms.userDomain.gen.model.UserResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface UserDtoMapper {

    UserDtoMapper INSTANCE = Mappers.getMapper(UserDtoMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    UserDomain toUserDomain(UserRequestDto userRequestDto);

    UserResponseDto toUserResponseDto(UserDomain userDomain);

    List<UserResponseDto> toUserResponseDtos(List<UserDomain> userDomains);
}
