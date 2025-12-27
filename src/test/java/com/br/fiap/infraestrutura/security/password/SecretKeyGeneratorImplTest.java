package com.br.fiap.infraestrutura.security.password;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SecretKeyGeneratorImplTest {

    @InjectMocks
    private SecretKeyGeneratorImpl secretKeyGeneratorImpl;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Test
    void criarSecretKey() {
        CharSequence password = "password";

        // Configurando mock para comportamento esperado
        when(passwordEncoder.encode(password))
                .thenReturn("encodedPassword123");

        String encoded = secretKeyGeneratorImpl.encode(password);

        assertNotNull(encoded);
    }
}