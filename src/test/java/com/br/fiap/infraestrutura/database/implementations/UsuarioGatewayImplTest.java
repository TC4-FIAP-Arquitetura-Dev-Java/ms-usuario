package com.br.fiap.infraestrutura.database.implementations;

import com.br.fiap.domain.model.UsuarioDomain;
import com.br.fiap.infraestrutura.database.entities.UsuarioDocument;
import com.br.fiap.infraestrutura.database.repositories.UsuarioRepository;
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
class UsuarioGatewayImplTest {

    @InjectMocks
    private UsuarioGatewayImpl usuarioGateway;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Test
    void buscarUsuarioPorId_sucesso() {
        String id = "1";
        UsuarioDocument usuarioDocument = getUsuarioDocument();
        when(usuarioRepository.findById(id)).thenReturn(Optional.of(usuarioDocument));

        Optional<UsuarioDomain> domain = usuarioGateway.buscarUsuarioPorId(id);

        assertNotNull(domain);
        verify(usuarioRepository, times(1)).findById(id);
    }

    @Test
    void salvar_sucesso() {
        UsuarioDomain usuarioDomain = getUsuarioDomain();
        UsuarioDocument usuarioDocument = getUsuarioDocument();
        when(usuarioRepository.save(usuarioDocument)).thenReturn(usuarioDocument);

        usuarioGateway.salvar(usuarioDomain);

        verify(usuarioRepository, times(1)).save(usuarioDocument);
    }

    @Test
    void deletar_sucesso() {
        UsuarioDomain usuarioDomain = getUsuarioDomain();
        UsuarioDocument usuarioDocument = getUsuarioDocument();
        doNothing().when(usuarioRepository).delete(usuarioDocument);

        usuarioGateway.deletar(usuarioDomain);

        verify(usuarioRepository, times(1)).delete(usuarioDocument);
    }

    @Test
    void verificarExistenciaEmailouUsuario_sucesso() {
        String email = "teste@teste.com";
        String usuario = "teste";
        when(usuarioRepository.findByEmailOrUsuario(email, usuario)).thenReturn(Optional.empty());

        Optional<UsuarioDomain> domain = usuarioGateway.verificarExistenciaEmailouUsuario(email, usuario);

        assertNotNull(domain);
        verify(usuarioRepository, times(1)).findByEmailOrUsuario(email, usuario);
    }
}
