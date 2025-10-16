package com.br.fiap.domain.model;

import java.time.OffsetDateTime;

public class UsuarioDomain {

    private String id;
    private String usuario;
    private String password;
    private String nome;
    private String email;
    private Boolean usuarioAtivo;
    private OffsetDateTime dataCriacao;
    private OffsetDateTime dataAlteracao;

    public UsuarioDomain(){}

    public UsuarioDomain(String id, String usuario, String password, String nome, String email, Boolean usuarioAtivo, OffsetDateTime dataCriacao, OffsetDateTime dataAlteracao) {
        this.id = id;
        this.usuario = usuario;
        this.password = password;
        this.nome = nome;
        this.email = email;
        this.usuarioAtivo = usuarioAtivo;
        this.dataCriacao = dataCriacao;
        this.dataAlteracao = dataAlteracao;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getUsuarioAtivo() {
        return usuarioAtivo;
    }

    public void setUsuarioAtivo(Boolean usuarioAtivo) {
        this.usuarioAtivo = usuarioAtivo;
    }

    public OffsetDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(OffsetDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public OffsetDateTime getDataAlteracao() {
        return dataAlteracao;
    }

    public void setDataAlteracao(OffsetDateTime dataAlteracao) {
        this.dataAlteracao = dataAlteracao;
    }
}
