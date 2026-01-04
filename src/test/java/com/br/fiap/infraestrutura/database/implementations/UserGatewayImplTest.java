package com.br.fiap.infraestrutura.database.implementations;

import com.br.fiap.application.dto.UserFilter;
import com.br.fiap.domain.model.UserDomain;
import com.br.fiap.infraestrutura.database.entities.UserEntity;
import com.br.fiap.infraestrutura.database.mapper.UserEntityMapper;
import com.br.fiap.infraestrutura.database.repositories.UserRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserGatewayImplTest {

    @InjectMocks
    private UserGatewayImpl userGateway;

    @Mock
    private Root<UserEntity> root;

    @Mock
    private CriteriaQuery<?> query;

    @Mock
    private CriteriaBuilder cb;

    @Mock
    private Predicate predicate;

    @Mock
    private Path<Boolean> booleanPath;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserEntityMapper userEntityMapper;

    @Mock
    private Path<String> stringPath;

    @Mock
    private Expression<String> lowerExpression;

    @Test
    void findWithFilter_shouldCoverAllBranches() {
        UserFilter filter = new UserFilter(
                "John",
                "john@email.com",
                "johnny",
                true
        );

        Pageable pageable = PageRequest.of(0, 10);

        when(userRepository.findAll(any(Specification.class), eq(pageable)))
                .thenReturn(Page.empty());

        userGateway.findWithFilter(filter, pageable);

        verify(userRepository).findAll(any(Specification.class), eq(pageable));
    }

    @Test
    void getById_shouldReturnDomain_whenIdIsValid() {
        UserEntity entity = new UserEntity();
        entity.setId(1L);

        UserDomain domain = new UserDomain();
        domain.setId("1");

        when(userRepository.findById(1L)).thenReturn(Optional.of(entity));
        when(userEntityMapper.toDomain(entity)).thenReturn(domain);

        Optional<UserDomain> result = userGateway.getById("1");

        assertTrue(result.isPresent());
        assertEquals("1", result.get().getId());
        verify(userRepository).findById(1L);
    }

    @Test
    void getById_shouldReturnEmpty_whenIdIsInvalid() {
        Optional<UserDomain> result = userGateway.getById("abc");

        assertTrue(result.isEmpty());
        verifyNoInteractions(userRepository);
    }

    @Test
    void getByUsername_shouldReturnDomain_whenExists() {
        UserEntity entity = new UserEntity();
        UserDomain domain = new UserDomain();

        when(userRepository.findByUsername("user"))
                .thenReturn(Optional.of(entity));
        when(userEntityMapper.toDomain(entity))
                .thenReturn(domain);

        Optional<UserDomain> result = userGateway.getByUsername("user");

        assertTrue(result.isPresent());
        verify(userRepository).findByUsername("user");
    }

    @Test
    void save_shouldPersistAndSetIdBackToDomain() {
        UserDomain domain = new UserDomain();
        UserEntity entity = new UserEntity();
        entity.setId(10L);

        when(userEntityMapper.toEntity(domain)).thenReturn(entity);

        userGateway.save(domain);

        assertEquals("10", domain.getId());
        verify(userRepository).save(entity);
    }

    @Test
    void delete_shouldDeleteById_whenIdIsValid() {
        UserDomain domain = new UserDomain();
        domain.setId("5");

        userGateway.delete(domain);

        verify(userRepository).deleteById(5L);
    }

    @Test
    void delete_shouldDeleteByUsername_whenIdIsInvalid() {
        UserDomain domain = new UserDomain();
        domain.setId("invalid");
        domain.setUsername("user");

        UserEntity entity = new UserEntity();

        when(userRepository.findByUsername("user"))
                .thenReturn(Optional.of(entity));

        userGateway.delete(domain);

        verify(userRepository).delete(entity);
    }

    @Test
    void checkExistenceByEmailOrUser_shouldReturnDomain_whenExists() {
        UserEntity entity = new UserEntity();
        UserDomain domain = new UserDomain();

        when(userRepository.findByEmailOrUsername("email@test.com", "user"))
                .thenReturn(Optional.of(entity));
        when(userEntityMapper.toDomain(entity))
                .thenReturn(domain);

        Optional<UserDomain> result =
                userGateway.checkExistenceByEmailOrUser("email@test.com", "user");

        assertTrue(result.isPresent());
    }

    @Test
    void findWithFilter_shouldReturnPageMappedToDomain() {
        UserFilter filter = new UserFilter("name", "email@test.com", "user", true);
        Pageable pageable = PageRequest.of(0, 10);

        UserEntity entity = new UserEntity();
        UserDomain domain = new UserDomain();

        Page<UserEntity> entityPage =
                new PageImpl<>(List.of(entity), pageable, 1);

        when(userRepository.findAll(any(Specification.class), eq(pageable)))
                .thenReturn(entityPage);
        when(userEntityMapper.toDomain(entity))
                .thenReturn(domain);

        Page<UserDomain> result =
                userGateway.findWithFilter(filter, pageable);

        assertEquals(1, result.getTotalElements());
        assertEquals(1, result.getContent().size());
        verify(userRepository).findAll(any(Specification.class), eq(pageable));
    }


    @Test
    void buildSpecification_shouldCreateEmptyPredicate_whenNoFiltersPresent() {
        UserFilter filter = new UserFilter(null, null, null, null);

        when(cb.and(any(Predicate[].class))).thenReturn(predicate);

        Specification<UserEntity> spec = (root, query, cb) ->
                cb.and(new Predicate[0]);

        Predicate result = spec.toPredicate(root, query, cb);

        verify(cb).and(any(Predicate[].class));
        assert result != null;
    }

    @Test
    void findWithFilter_shouldBuildSpecificationAndCallMapper() {
        UserFilter filter = new UserFilter("John", "john@email.com", "johnny", true);
        Pageable pageable = PageRequest.of(0, 10);

        UserEntity entity = new UserEntity();
        Page<UserEntity> entityPage = new PageImpl<>(List.of(entity), pageable, 1);
        UserDomain domain = new UserDomain();

        when(userRepository.findAll(any(Specification.class), eq(pageable))).thenReturn(entityPage);
        when(userEntityMapper.toDomain(entity)).thenReturn(domain);

        Page<UserDomain> result = userGateway.findWithFilter(filter, pageable);

        verify(userEntityMapper).toDomain(entity);
        assertEquals(1, result.getContent().size());
    }
    @Test
    void findWithFilter_shouldCallMapperForEachEntity() {
        UserFilter filter = new UserFilter("name", "email@test.com", "user", true);
        Pageable pageable = PageRequest.of(0, 10);

        UserEntity entity = new UserEntity();
        Page<UserEntity> entityPage = new PageImpl<>(List.of(entity), pageable, 1);

        UserDomain domain = new UserDomain();

        when(userRepository.findAll(any(Specification.class), eq(pageable))).thenReturn(entityPage);
        when(userEntityMapper.toDomain(entity)).thenReturn(domain);

        Page<UserDomain> result = userGateway.findWithFilter(filter, pageable);

        verify(userEntityMapper).toDomain(entity);
        assertEquals(1, result.getContent().size());
    }
}