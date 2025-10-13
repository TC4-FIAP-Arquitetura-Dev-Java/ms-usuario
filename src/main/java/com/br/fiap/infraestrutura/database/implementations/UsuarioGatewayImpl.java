package com.br.fiap.infraestrutura.database.implementations;

import com.br.fiap.application.gateways.UsuarioGateway;
import com.br.fiap.domain.model.UsuarioDomain;
import com.br.fiap.infraestrutura.database.entities.UsuarioDocument;
import com.br.fiap.infraestrutura.database.mapper.UsuarioDocumentMapper;
import com.br.fiap.infraestrutura.database.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioGatewayImpl implements UsuarioGateway {

    private final UsuarioRepository usuarioRepository;

    @Override
    public Optional<UsuarioDomain> buscarUsuarioPorId(String id) {
        return usuarioRepository.findById(id)
                .map(UsuarioDocumentMapper.INSTANCE::toDomain);
    }

    @Override
    public void salvar(UsuarioDomain domain) {
        UsuarioDocument usuarioDocument = UsuarioDocumentMapper.INSTANCE.toDocument(domain);
        usuarioRepository.save(usuarioDocument);
    }

    @Override
    public void deletar(UsuarioDomain domain) {
        UsuarioDocument usuarioDocument = UsuarioDocumentMapper.INSTANCE.toDocument(domain);
        usuarioRepository.delete(usuarioDocument);
    }

    @Override
    public Optional<UsuarioDomain> verificarExistenciaEmailouUsuario(String email, String usuario) {
        return usuarioRepository.findByEmailOrUsuario(email, usuario)
                .map(UsuarioDocumentMapper.INSTANCE::toDomain);
    }
}
