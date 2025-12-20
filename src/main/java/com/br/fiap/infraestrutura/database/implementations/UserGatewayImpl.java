package com.br.fiap.infraestrutura.database.implementations;

import com.br.fiap.application.dto.UserFilter;
import com.br.fiap.application.gateways.UserGateway;
import com.br.fiap.domain.model.UserDomain;
import com.br.fiap.infraestrutura.database.entities.UserDocument;
import com.br.fiap.infraestrutura.database.mapper.UserDocumentMapper;
import com.br.fiap.infraestrutura.database.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;
import java.util.Optional;

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
    public List<UserDomain> findAll(UserFilter filter) {
        Query query = buildQuery(filter);

        return mongoTemplate
                .find(query, UserDocument.class)
                .stream()
                .map(userDocumentMapper::toDomain)
                .toList();
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

    private Query buildQuery(UserFilter filter) {
        Query query = new Query();

        if(filter == null){
            filter = UserFilter.empty();
        }

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

        //Limit
        if (filter.limit() != null && filter.limit() > 0) {
            query.limit(filter.limit());
        }

        // offset
        if (filter.offset() != null) {
            int offset = filter.offset();

            if (offset < 0) {
                offset = 0;
            }
            query.skip(offset);
        }
        return query;
    }
}
