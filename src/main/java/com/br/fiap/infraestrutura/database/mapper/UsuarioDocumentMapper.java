package com.br.fiap.infraestrutura.database.mapper;

import com.br.fiap.domain.model.UsuarioDomain;
import com.br.fiap.infraestrutura.database.entities.UsuarioDocument;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface UsuarioDocumentMapper {

    UsuarioDocumentMapper INSTANCE = Mappers.getMapper(UsuarioDocumentMapper.class);

    @Mapping(target = "dataCriacao", expression = "java(usuarioDocument.getDataCriacao() != null ? usuarioDocument.getDataCriacao().atOffset(java.time.ZoneOffset.UTC) : null)")
    @Mapping(target = "dataAlteracao", expression = "java(usuarioDocument.getDataAlteracao() != null ? usuarioDocument.getDataAlteracao().atOffset(java.time.ZoneOffset.UTC) : null)")
    UsuarioDomain toDomain(UsuarioDocument usuarioDocument);

    @Mapping(target = "dataCriacao", expression = "java(usuarioDomain.getDataCriacao() != null ? usuarioDomain.getDataCriacao().toInstant() : null)")
    @Mapping(target = "dataAlteracao", expression = "java(usuarioDomain.getDataAlteracao() != null ? usuarioDomain.getDataAlteracao().toInstant() : null)")
    UsuarioDocument toDocument(UsuarioDomain usuarioDomain);
}
