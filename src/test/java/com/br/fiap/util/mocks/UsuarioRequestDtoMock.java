package com.br.fiap.util.mocks;

import com.fiap.ms.userDomain.gen.model.UserRequestDto;

public class UsuarioRequestDtoMock {

    public static UserRequestDto getUserRequestDto() {
        UserRequestDto request = new UserRequestDto();
        request.setUsername("marcos.silva");
        request.setName("Marcos Silva");
        request.setEmail("marcos.silva@email.com");
        request.setActiveUser(true);
        return request;
    }
}
