package com.br.fiap.infraestrutura.config.mongodb;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.auditing.DateTimeProvider;

import java.time.OffsetDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class) // <- conforme solicitado
class MongoConfigTest {

    @InjectMocks
    private MongoConfig mongoConfig; // cria a instância da config para teste

    @Test
    void deveRetornarInstanciaDeDateTimeProvider() {
        DateTimeProvider provider = mongoConfig.dateTimeProvider();

        assertThat(provider).isNotNull();

        Optional<?> date = provider.getNow();

        assertThat(date).isPresent();
        assertThat(date.get()).isInstanceOf(OffsetDateTime.class);
    }
}