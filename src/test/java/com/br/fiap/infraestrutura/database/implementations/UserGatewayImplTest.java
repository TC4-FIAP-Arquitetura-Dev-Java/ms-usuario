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
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserGatewayImplTest {

    @InjectMocks
    private UserGatewayImpl usuarioGateway;

    @Mock
    private UserRepository userRepository;

    @Test
    void buscarUsuarioPorId_sucesso() {
        String id = "1";
        UserDocument userDocument = getUsuarioDocument();
        when(userRepository.findById(id)).thenReturn(Optional.of(userDocument));

        Optional<UserDomain> domain = usuarioGateway.getById(id);

        assertNotNull(domain);
        verify(userRepository, times(1)).findById(id);
    }

    @Test
    void salvar_sucesso() {
        UserDomain userDomain = getUsuarioDomain();
        UserDocument userDocument = getUsuarioDocument();
        when(userRepository.save(userDocument)).thenReturn(userDocument);

        usuarioGateway.save(userDomain);

        verify(userRepository, times(1)).save(userDocument);
    }

    @Test
    void deletar_sucesso() {
        UserDomain userDomain = getUsuarioDomain();
        UserDocument userDocument = getUsuarioDocument();
        doNothing().when(userRepository).delete(userDocument);

        usuarioGateway.delete(userDomain);

        verify(userRepository, times(1)).delete(userDocument);
    }

    @Test
    void verificarExistenciaEmailouUsuario_sucesso() {
        String email = "teste@teste.com";
        String usuario = "teste";
        when(userRepository.findByEmailOrUsuario(email, usuario)).thenReturn(Optional.empty());

        Optional<UserDomain> domain = usuarioGateway.checkExistenceByEmailOrUser(email, usuario);

        assertNotNull(domain);
        verify(userRepository, times(1)).findByEmailOrUsuario(email, usuario);
    }
}
