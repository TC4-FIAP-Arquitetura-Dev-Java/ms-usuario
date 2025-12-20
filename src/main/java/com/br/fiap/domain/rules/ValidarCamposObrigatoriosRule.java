package com.br.fiap.domain.rules;

import com.br.fiap.domain.exception.FieldRequiredException;
import com.br.fiap.domain.model.UserDomain;

public class ValidarCamposObrigatoriosRule {

    public static void validarCamposObrigatorios(UserDomain userDomain) {
        if (isNuloOuVazio(userDomain.getUsuario()) &&
                isNuloOuVazio(userDomain.getNome()) &&
                isNuloOuVazio(userDomain.getPassword()) &&
                isNuloOuVazio(userDomain.getEmail())) {
            throw new FieldRequiredException("Existem campos obrigatorios que não foram preenchidos");
        }
    }

    private static boolean isNuloOuVazio(String campo) {
        return campo == null || campo.trim().isEmpty();
    }
}
