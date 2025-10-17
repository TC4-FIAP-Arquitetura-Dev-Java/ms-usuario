package com.br.fiap.domain.rules;

import com.br.fiap.domain.exception.CampoObrigatorioException;
import com.br.fiap.domain.model.UsuarioDomain;

public class ValidarCamposObrigatoriosRule {

    public static void validarCamposObrigatorios(UsuarioDomain usuarioDomain) {
        if (isNuloOuVazio(usuarioDomain.getUsuario()) &&
                isNuloOuVazio(usuarioDomain.getNome()) &&
                isNuloOuVazio(usuarioDomain.getPassword()) &&
                isNuloOuVazio(usuarioDomain.getEmail())) {
            throw new CampoObrigatorioException("Existem campos obrigatorios que não foram preenchidos");
        }
    }

    private static boolean isNuloOuVazio(String campo) {
        return campo == null || campo.trim().isEmpty();
    }
}
