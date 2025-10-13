package com.br.fiap.util.mocks;

import com.fiap.ms.usuarioDomain.gen.model.UsuarioRequestDto;

public class UsuarioRequestDtoMock {

    public static UsuarioRequestDto getUsuarioRequestDto() {
        UsuarioRequestDto request = new UsuarioRequestDto();
        request.setUsuario("marcos.silva");
        request.setNome("Marcos Silva");
        request.setEmail("marcos.silva@email.com");
        request.setUsuarioAtivo(true);
        return request;
    }
}
