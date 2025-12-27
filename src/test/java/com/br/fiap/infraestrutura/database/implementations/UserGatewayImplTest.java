package com.br.fiap.infraestrutura.database.implementations;

import com.br.fiap.domain.model.UserDomain;
import com.br.fiap.infraestrutura.database.entities.UserDocument;
import com.br.fiap.infraestrutura.database.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;

import static com.br.fiap.util.mocks.UsuarioDocumentMock.getUsuarioDocument;
import static com.br.fiap.util.mocks.UsuarioDomainMocks.getUsuarioDomain;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserGatewayImplTest {

    @InjectMocks
    private UserGatewayImpl usuarioGateway;

    @Mock
    private UserRepository userRepository;

    // ---------- TESTES ---------- //

    @Test
    void buscarUsuarioPorId_sucesso() {
        String id = "1";
        UserDocument userDocument = getUsuarioDocument();

        when(userRepository.findById(id)).thenReturn(Optional.of(userDocument));

        Optional<UserDomain> domain = usuarioGateway.getById(id);

        assertTrue(domain.isPresent());
        verify(userRepository).findById(id);
    }

    @Test
    void buscarPorUsuario_sucesso() {
        String username = "marcos.silva";
        UserDocument userDocument = getUsuarioDocument();

        when(userRepository.findByUsername(username)).thenReturn(Optional.of(userDocument));

        Optional<UserDomain> domain = usuarioGateway.getByUsername(username);

        assertTrue(domain.isPresent());
        verify(userRepository).findByUsername(username);
    }

    @Test
    void salvar_sucesso() {
        UserDomain userDomain = getUsuarioDomain();
        UserDocument userDocument = getUsuarioDocument();

        // qualquer instância do tipo UserDocument será retornada
        when(userRepository.save(any(UserDocument.class))).thenReturn(userDocument);

        usuarioGateway.save(userDomain);

        verify(userRepository).save(any(UserDocument.class));
    }

    @Test
    void deletar_sucesso() {
        UserDomain userDomain = getUsuarioDomain();

        doNothing().when(userRepository).delete(any(UserDocument.class));

        usuarioGateway.delete(userDomain);

        verify(userRepository).delete(any(UserDocument.class));
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

