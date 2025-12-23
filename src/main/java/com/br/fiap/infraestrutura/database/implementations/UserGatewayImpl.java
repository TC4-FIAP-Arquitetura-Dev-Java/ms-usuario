package com.br.fiap.infraestrutura.database.implementations;

import com.br.fiap.application.dto.UserFilter;
import com.br.fiap.application.gateways.UserGateway;
import com.br.fiap.domain.model.UserDomain;
import com.br.fiap.infraestrutura.database.entities.UserDocument;
import com.br.fiap.infraestrutura.database.mapper.UserDocumentMapper;
import com.br.fiap.infraestrutura.database.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserGatewayImpl implements UserGateway {

    private final UserRepository userRepository;
    private final MongoTemplate mongoTemplate;
    private final UserDocumentMapper userDocumentMapper;

    @Override
    public Optional<UserDomain> getById(String id) {
        return userRepository.findById(id)
                .map(UserDocumentMapper.INSTANCE::toDomain);
    }

    @Override
    public Optional<UserDomain> getByUsername(String username) {
        return userRepository.findByUsuario(username)
                .map(UserDocumentMapper.INSTANCE::toDomain);
    }

    @Override
    public void save(UserDomain domain) {
        UserDocument userDocument = UserDocumentMapper.INSTANCE.toDocument(domain);
        userRepository.save(userDocument);
    }

    @Override
    public void delete(UserDomain domain) {
        UserDocument userDocument = UserDocumentMapper.INSTANCE.toDocument(domain);
        userRepository.delete(userDocument);
    }

    @Override
    public Optional<UserDomain> checkExistenceByEmailOrUser(String email, String usuario) {
        return userRepository.findByEmailOrUsuario(email, usuario)
                .map(UserDocumentMapper.INSTANCE::toDomain);
    }

    @Override
    public Page<UserDomain> findWithFilter(UserFilter filter, Pageable pageable) {

        Query query = buildQuery(filter);
        long total = mongoTemplate.count(query, UserDocument.class);
        query.with(pageable);

        List<UserDomain> userDomains = mongoTemplate.find(query, UserDocument.class)
                .stream()
                .map(UserDocumentMapper.INSTANCE::toDomain)
                .collect(Collectors.toList());

        return new PageImpl<>(userDomains, pageable, total);
    }

    private Query buildQuery(UserFilter filter) {
        Query query = new Query();

        // Filter by name
        if (filter.name() != null && !filter.name().isBlank()) {
            query.addCriteria(Criteria.where("name").regex(filter.name().trim(), "i"));
        }

        // Filter by email
        if (filter.email() != null && filter.email().isBlank()) {
            query.addCriteria(Criteria.where("email").is(filter.email().trim()));
        }

        // Filter by username
        if (filter.username() != null && filter.username().isBlank()) {
            query.addCriteria(Criteria.where("username").is(filter.username().trim()));
        }

        if (filter.activeUser() != null) {
            query.addCriteria(Criteria.where("activeUser").is(filter.activeUser().booleanValue()));
        }
        return query;
    }
}
