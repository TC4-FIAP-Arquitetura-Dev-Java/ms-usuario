package com.br.fiap.infraestrutura.database.implementations;

import com.br.fiap.domain.model.UserDomain;
import com.br.fiap.infraestrutura.database.entities.UserEntity;
import com.br.fiap.infraestrutura.database.mapper.UserEntityMapper;
import com.br.fiap.infraestrutura.database.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;

import static com.br.fiap.util.mocks.UsuarioEntityMock.getUsuarioEntity;
import static com.br.fiap.util.mocks.UsuarioDomainMocks.getUsuarioDomain;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserGatewayImplTest {

    @InjectMocks
    private UserGatewayImpl usuarioGateway;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserEntityMapper userEntityMapper;

    // ---------- TESTES ---------- //

    @Test
    void buscarUsuarioPorId_sucesso() {
        String id = "1";
        Long userId = 1L;
        UserEntity userEntity = getUsuarioEntity();
        UserDomain userDomain = getUsuarioDomain();

        when(userRepository.findById(userId)).thenReturn(Optional.of(userEntity));
        when(userEntityMapper.toDomain(userEntity)).thenReturn(userDomain);

        Optional<UserDomain> domain = usuarioGateway.getById(id);

        assertTrue(domain.isPresent());
        verify(userRepository).findById(userId);
        verify(userEntityMapper).toDomain(userEntity);
    }

    @Test
    void buscarPorUsuario_sucesso() {
        String username = "marcos.silva";
        UserEntity userEntity = getUsuarioEntity();
        UserDomain userDomain = getUsuarioDomain();

        when(userRepository.findByUsername(username)).thenReturn(Optional.of(userEntity));
        when(userEntityMapper.toDomain(userEntity)).thenReturn(userDomain);

        Optional<UserDomain> domain = usuarioGateway.getByUsername(username);

        assertTrue(domain.isPresent());
        verify(userRepository).findByUsername(username);
        verify(userEntityMapper).toDomain(userEntity);
    }

    @Test
    void salvar_sucesso() {
        UserDomain userDomain = getUsuarioDomain();
        UserEntity userEntity = getUsuarioEntity();

        when(userEntityMapper.toEntity(userDomain)).thenReturn(userEntity);
        when(userRepository.save(any(UserEntity.class))).thenReturn(userEntity);

        usuarioGateway.save(userDomain);

        verify(userEntityMapper).toEntity(userDomain);
        verify(userRepository).save(any(UserEntity.class));
    }

    @Test
    void deletar_sucesso() {
        UserDomain userDomain = getUsuarioDomain();
        Long userId = Long.parseLong(userDomain.getId());

        doNothing().when(userRepository).deleteById(userId);

        usuarioGateway.delete(userDomain);

        verify(userRepository).deleteById(userId);
    }

    @Test
    void verificarExistenciaEmailouUsuario_sucesso() {
        String email = "teste@teste.com";
        String usuario = "teste";

        when(userRepository.findByEmailOrUsername(email, usuario)).thenReturn(Optional.empty());

        Optional<UserDomain> domain = usuarioGateway.checkExistenceByEmailOrUser(email, usuario);

        assertTrue(domain.isEmpty());
        verify(userRepository).findByEmailOrUsername(email, usuario);
    }
}
