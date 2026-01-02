package com.br.fiap.infraestrutura.database.repositories;

import com.br.fiap.infraestrutura.database.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long>, JpaSpecificationExecutor<UserEntity> {

    Optional<UserEntity> findByEmailOrUsername(String email, String username);

    Optional<UserEntity> findByUsername(String username);
}
