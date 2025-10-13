package com.br.fiap.infraestrutura.database.entities;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Data
@Document(collection = "usuario")
public class UsuarioDocument {

    @Id
    private String id;

    @Indexed(unique = true)
    private String usuario;

    @Indexed(unique = true)
    private String nome;
    private String email;
    private Boolean usuarioAtivo;

    @CreatedDate
    private Instant dataCriacao;

    @LastModifiedDate
    private Instant dataAlteracao;
}