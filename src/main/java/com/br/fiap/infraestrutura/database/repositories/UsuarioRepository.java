package com.br.fiap.infraestrutura.database.repositories;

import com.br.fiap.infraestrutura.database.entities.UsuarioDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UsuarioRepository extends MongoRepository<UsuarioDocument, String> {

    Optional<UsuarioDocument> findByEmailOrUsuario(String email, String usuario);

    Optional<UsuarioDocument> findByUsuario(String usuario);
}
