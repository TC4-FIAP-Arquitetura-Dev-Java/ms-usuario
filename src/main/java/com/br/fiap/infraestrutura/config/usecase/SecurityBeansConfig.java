package com.br.fiap.infraestrutura.config.usecase;

import com.br.fiap.application.gateways.SecretKeyGenerator;
import com.br.fiap.infraestrutura.security.password.SecretKeyGeneratorImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityBeansConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecretKeyGenerator secretKeyGenerator(PasswordEncoder passwordEncoder) {
        return new SecretKeyGeneratorImpl(passwordEncoder);
    }
}
