package com.br.fiap.application.usecase.implementations;

import com.br.fiap.domain.domainService.UserDomainService;
import com.br.fiap.domain.model.UserDomain;
import com.br.fiap.util.mocks.UsuarioDomainMocks;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetByUsernameImplTest {

    @InjectMocks
    private GetByUsernameImpl getByUsernameImpl;

    @Mock
    private UserDomainService userDomainService;

    @Test
    void getByUsername() {
        String username = "marcos.silva";
        UserDomain userDomain = UsuarioDomainMocks.getUsuarioDomain();

        // ⛔ Antes faltava isso
        when(userDomainService.getByUsername(username))
                .thenReturn(userDomain);

        UserDomain domain = getByUsernameImpl.getByUsername(username);

        assertNotNull(domain);
        assertEquals(username, domain.getUsername());
    }
}