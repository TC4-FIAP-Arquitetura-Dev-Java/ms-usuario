package com.br.fiap.infraestrutura.database.implementations;

import com.br.fiap.application.dto.UserFilter;
import com.br.fiap.application.gateways.UserGateway;
import com.br.fiap.domain.model.UserDomain;
import com.br.fiap.infraestrutura.database.entities.UserEntity;
import com.br.fiap.infraestrutura.database.mapper.UserEntityMapper;
import com.br.fiap.infraestrutura.database.repositories.UserRepository;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserGatewayImpl implements UserGateway {

    private final UserRepository userRepository;
    private final UserEntityMapper userEntityMapper;

    @Override
    public Optional<UserDomain> getById(String id) {
        try {
            Long userId = Long.parseLong(id);
            return userRepository.findById(userId)
                    .map(userEntityMapper::toDomain);
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<UserDomain> getByUsername(String username) {
        return userRepository.findByUsername(username)
                .map(userEntityMapper::toDomain);
    }

    @Override
    public void save(UserDomain domain) {
        UserEntity userEntity = userEntityMapper.toEntity(domain);
        userRepository.save(userEntity);
        if (userEntity.getId() != null) {
            domain.setId(userEntity.getId().toString());
        }
    }

    @Override
    public void delete(UserDomain domain) {
        try {
            Long userId = Long.parseLong(domain.getId());
            userRepository.deleteById(userId);
        } catch (NumberFormatException e) {
            userRepository.findByUsername(domain.getUsername())
                    .ifPresent(userRepository::delete);
        }
    }

    @Override
    public Optional<UserDomain> checkExistenceByEmailOrUser(String email, String username) {
        return userRepository.findByEmailOrUsername(email, username)
                .map(userEntityMapper::toDomain);
    }

    @Override
    public Page<UserDomain> findWithFilter(UserFilter filter, Pageable pageable) {
        Specification<UserEntity> spec = buildSpecification(filter);
        Page<UserEntity> userEntities = userRepository.findAll(spec, pageable);

        List<UserDomain> userDomains = userEntities.getContent().stream()
                .map(userEntityMapper::toDomain)
                .collect(Collectors.toList());

        return new PageImpl<>(userDomains, pageable, userEntities.getTotalElements());
    }

    private Specification<UserEntity> buildSpecification(UserFilter filter) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filter.name() != null && !filter.name().isBlank()) {
                predicates.add(cb.like(
                    cb.lower(root.get("name")),
                    "%" + filter.name().trim().toLowerCase() + "%"
                ));
            }

            if (filter.email() != null && !filter.email().isBlank()) {
                predicates.add(cb.equal(root.get("email"), filter.email().trim()));
            }

            if (filter.username() != null && !filter.username().isBlank()) {
                predicates.add(cb.equal(root.get("username"), filter.username().trim()));
            }

            if (filter.activeUser() != null) {
                predicates.add(cb.equal(root.get("activeUser"), filter.activeUser()));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
