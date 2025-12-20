package com.br.fiap.entrypoinsts.controllers.mappers;

import com.br.fiap.application.dto.UserFilter;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface UserFilterMapper {

    UserFilterMapper INSTANCE = Mappers.getMapper(UserFilterMapper.class);

    UserFilter toFilter(String name, String email, String username, Boolean active, Integer limit, Integer offset);

}
