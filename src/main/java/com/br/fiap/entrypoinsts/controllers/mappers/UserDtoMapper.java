package com.br.fiap.entrypoinsts.controllers.mappers;

import com.br.fiap.domain.enums.RoleEnum;
import com.br.fiap.domain.model.UserDomain;
import com.fiap.ms.userDomain.gen.model.RoleEnumDto;
import com.fiap.ms.userDomain.gen.model.UserRequestDto;
import com.fiap.ms.userDomain.gen.model.UserResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface UserDtoMapper {

    UserDtoMapper INSTANCE = Mappers.getMapper(UserDtoMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(source = "roleEnum", target = "roleEnum", qualifiedByName = "toRoleEnum")
    UserDomain toUserDomain(UserRequestDto userRequestDto);

    @Mapping(source = "roleEnum", target = "roleEnum", qualifiedByName = "toRoleEnumDto")
    UserResponseDto toUserResponseDto(UserDomain userDomain);

    List<UserResponseDto> toUserResponseDtos(List<UserDomain> userDomains);

    @Named("toRoleEnum")
    default RoleEnum mapRole(String role) {
        if (role == null) return null;
        return RoleEnum.valueOf(role.toUpperCase());
    }

    @Named("toRoleEnumDto")
    default RoleEnumDto mapRoleDto(String role) {
        if (role == null) return null;
        return RoleEnumDto.valueOf(role.toUpperCase());
    }
}
