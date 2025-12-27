package com.br.fiap.infraestrutura.config.domainService;

import com.br.fiap.application.gateways.UserGateway;
import com.br.fiap.domain.domainService.implementations.UserDomainServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UsuarioDomainServiceConfigTest {

    @Mock
    private UserGateway userGateway;

    @Test
    void deveCriarBeanUsuarioDomainService() {
        // Arrange
        UsuarioDomainServiceConfig config = new UsuarioDomainServiceConfig();

        // Act
        UserDomainServiceImpl service = config.usuarioDomainService(userGateway);

        // Assert
        assertNotNull(service, "O bean UserDomainServiceImpl deve ser criado");
    }
}