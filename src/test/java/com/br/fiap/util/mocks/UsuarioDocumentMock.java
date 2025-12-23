package com.br.fiap.util.mocks;

import com.br.fiap.infraestrutura.database.entities.UserDocument;

import java.time.Instant;

public class UsuarioDocumentMock {

    public static UserDocument getUsuarioDocument() {
        UserDocument document = new UserDocument();
        document.setId("123");
        document.setUsername("marcos.silva");
        document.setName("Marcos Silva");
        document.setEmail("marcos.silva@email.com");
        document.setPassword("asd45fas65d4fas54fa6sd5f4as65d1fsad56f");
        document.setActiveUser(true);
        document.setCreatedAt(Instant.parse("2024-10-10T12:00:00Z"));
        document.setUpdatedAt(Instant.parse("2024-10-11T14:30:00Z"));
        return document;
    }
}
