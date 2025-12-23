package com.br.fiap.infraestrutura.database.repositories;

import com.br.fiap.infraestrutura.database.entities.UserDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<UserDocument, String> {

    Optional<UserDocument> findByEmailOrUsername(String email, String usuario);

    Optional<UserDocument> findByUsername(String usuario);
}
