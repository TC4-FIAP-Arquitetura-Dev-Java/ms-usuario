package com.br.fiap.infraestrutura.database.mapper;

import com.br.fiap.domain.model.UserDomain;
import com.br.fiap.infraestrutura.database.entities.UserDocument;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface UserDocumentMapper {

    UserDocumentMapper INSTANCE = Mappers.getMapper(UserDocumentMapper.class);

    @Mapping(target = "createdAt", expression = "java(userDocument.getCreatedAt() != null ? userDocument.getCreatedAt().atOffset(java.time.ZoneOffset.UTC) : null)")
    @Mapping(target = "updatedAt", expression = "java(userDocument.getUpdatedAt() != null ? userDocument.getUpdatedAt().atOffset(java.time.ZoneOffset.UTC) : null)")
    UserDomain toDomain(UserDocument userDocument);

    @Mapping(target = "createdAt", expression = "java(userDomain.getCreatedAt() != null ? userDomain.getCreatedAt().toInstant() : null)")
    @Mapping(target = "updatedAt", expression = "java(userDomain.getUpdatedAt() != null ? userDomain.getUpdatedAt().toInstant() : null)")
    UserDocument toDocument(UserDomain userDomain);
}
