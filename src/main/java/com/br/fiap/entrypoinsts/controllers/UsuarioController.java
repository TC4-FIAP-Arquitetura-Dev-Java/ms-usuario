package com.br.fiap.entrypoinsts.controllers;

import com.br.fiap.application.usecase.*;
import com.br.fiap.domain.model.UsuarioDomain;
import com.br.fiap.entrypoinsts.controllers.presenter.UsuarioPresenter;
import com.fiap.ms.usuarioDomain.UsuariosApi;
import com.fiap.ms.usuarioDomain.gen.model.BuscarUsuariosPaginadoDto;
import com.fiap.ms.usuarioDomain.gen.model.UsuarioRequestDto;
import com.fiap.ms.usuarioDomain.gen.model.UsuarioResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/v1")
public class UsuarioController implements UsuariosApi {

    private final AtualizarUsuarioUseCase atualizarUsuarioUseCase;
    private final BuscarUsuariosPaginadoUseCase buscarUsuariosPaginadoUseCase;
    private final ConsultarPorUsuarioUseCase consultarPorUsuarioUseCase;
    private final ConsultarUsuarioPorIdUseCase consultarUsuarioPorIdUseCase;
    private final CriarUsuarioUseCase criarUsuarioUseCase;
    private final ExcluirUsuarioUseCase excluirUsuarioUseCase;

    public UsuarioController(AtualizarUsuarioUseCase atualizarUsuarioUseCase,
                             BuscarUsuariosPaginadoUseCase buscarUsuariosPaginadoUseCase,
                             ConsultarUsuarioPorIdUseCase consultarUsuarioPorIdUseCase,
                             ConsultarPorUsuarioUseCase consultarPorUsuarioUseCase,
                             CriarUsuarioUseCase criarUsuarioUseCase,
                             ExcluirUsuarioUseCase excluirUsuarioUseCase) {
        this.atualizarUsuarioUseCase = atualizarUsuarioUseCase;
        this.buscarUsuariosPaginadoUseCase = buscarUsuariosPaginadoUseCase;
        this.consultarUsuarioPorIdUseCase = consultarUsuarioPorIdUseCase;
        this.consultarPorUsuarioUseCase = consultarPorUsuarioUseCase;
        this.criarUsuarioUseCase = criarUsuarioUseCase;
        this.excluirUsuarioUseCase = excluirUsuarioUseCase;
    }

    @Override
    public ResponseEntity<Void> _atualizarUsuario(String id, UsuarioRequestDto usuarioRequestDto) {
        UsuarioDomain usuarioDomain = UsuarioPresenter.toUsuarioDomain(usuarioRequestDto);
        atualizarUsuarioUseCase.atualizar(id, usuarioDomain);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<BuscarUsuariosPaginadoDto> _buscarUsuariosPaginado(String usuario, Boolean usuarioAtivo, Integer page, Integer size, String sort) {
        return ResponseEntity.ok(null);
    }

//    TODO: CORRIGIR O ENDPOINT DE CONSULTA POR USERNAME PARA NÃO TER CONFLITO
    @Override
    public ResponseEntity<UsuarioResponseDto> _consultarPorUsuario(String usuario) {
        UsuarioDomain usuarioDomain = consultarPorUsuarioUseCase.buscarPorUsuario(usuario);
        UsuarioResponseDto usuarioResponseDto = UsuarioPresenter.toUsuarioRequestDto(usuarioDomain);
        return ResponseEntity.ok(usuarioResponseDto);
    }

    @Override
    public ResponseEntity<UsuarioResponseDto> _consultarUsuarioPorId(String id) {
        UsuarioDomain usuarioDomain = consultarUsuarioPorIdUseCase.buscarUsuarioPorId(id);
        UsuarioResponseDto usuarioResponseDto = UsuarioPresenter.toUsuarioRequestDto(usuarioDomain);
        return ResponseEntity.ok(usuarioResponseDto);
    }

    @Override
    public ResponseEntity<Void> _criarUsuario(UsuarioRequestDto usuarioRequestDto) {
        //TODO: AO CRIAR UM USUARIO ESSE MS DEVE CRIPTOGRAFAR A SENHA (PASSWORD)
        UsuarioDomain usuarioDomain = UsuarioPresenter.toUsuarioDomain(usuarioRequestDto);
        criarUsuarioUseCase.criar(usuarioDomain);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Override
    public ResponseEntity<Void> _excluirUsuario(String id) {
        excluirUsuarioUseCase.deletar(id);
        return ResponseEntity.noContent().build();
    }
}