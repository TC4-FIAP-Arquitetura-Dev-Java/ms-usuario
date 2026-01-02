package com.br.fiap.infraestrutura.database.mapper;

import com.br.fiap.domain.model.UserDomain;
import com.br.fiap.infraestrutura.database.entities.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface UserEntityMapper {

    UserEntityMapper INSTANCE = Mappers.getMapper(UserEntityMapper.class);

    @Mapping(target = "createdAt", expression = "java(userEntity.getCreatedAt() != null ? userEntity.getCreatedAt().atOffset(java.time.ZoneOffset.UTC) : null)")
    @Mapping(target = "updatedAt", expression = "java(userEntity.getUpdatedAt() != null ? userEntity.getUpdatedAt().atOffset(java.time.ZoneOffset.UTC) : null)")
    @Mapping(target = "id", expression = "java(userEntity.getId() != null ? userEntity.getId().toString() : null)")
    UserDomain toDomain(UserEntity userEntity);

    @Mapping(target = "createdAt", expression = "java(userDomain.getCreatedAt() != null ? userDomain.getCreatedAt().toInstant() : null)")
    @Mapping(target = "updatedAt", expression = "java(userDomain.getUpdatedAt() != null ? userDomain.getUpdatedAt().toInstant() : null)")
    @Mapping(target = "id", expression = "java(userDomain.getId() != null && !userDomain.getId().isEmpty() ? Long.parseLong(userDomain.getId()) : null)")
    UserEntity toEntity(UserDomain userDomain);
}


