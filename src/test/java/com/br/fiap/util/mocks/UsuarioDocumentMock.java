package com.br.fiap.util.mocks;

import com.br.fiap.infraestrutura.database.entities.UsuarioDocument;

import java.time.Instant;

public class UsuarioDocumentMock {

    public static UsuarioDocument getUsuarioDocument() {
        UsuarioDocument document = new UsuarioDocument();
        document.setId("123");
        document.setUsuario("marcos.silva");
        document.setNome("Marcos Silva");
        document.setEmail("marcos.silva@email.com");
        document.setUsuarioAtivo(true);
        document.setDataCriacao(Instant.parse("2024-10-10T12:00:00Z"));
        document.setDataAlteracao(Instant.parse("2024-10-11T14:30:00Z"));
        return document;
    }
}
