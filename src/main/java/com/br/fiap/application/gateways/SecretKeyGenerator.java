package com.br.fiap.application.gateways;

public interface SecretKeyGenerator {

    String encode(CharSequence rawPassword);
}
