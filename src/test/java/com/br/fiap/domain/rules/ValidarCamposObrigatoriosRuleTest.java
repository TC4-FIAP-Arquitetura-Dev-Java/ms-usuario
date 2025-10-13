package com.br.fiap.domain.rules;

import com.br.fiap.domain.exception.CampoObrigatorioException;
import com.br.fiap.domain.model.UsuarioDomain;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static com.br.fiap.util.mocks.UsuarioDomainMocks.getUsuarioDomain;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class ValidarCamposObrigatoriosRuleTest {

    @Test
    void deveValidarCamposObrigatoriosQuandoTodosPreenchidos() {
        UsuarioDomain usuario = getUsuarioDomain();
        assertDoesNotThrow(() -> ValidarCamposObrigatoriosRule.validarCamposObrigatorios(usuario));
    }

    @Test
    void deveLancarExcecaoQuandoTodosCamposNulos() {
        UsuarioDomain usuario = new UsuarioDomain();

        CampoObrigatorioException exception = assertThrows(
                CampoObrigatorioException.class,
                () -> ValidarCamposObrigatoriosRule.validarCamposObrigatorios(usuario)
        );

        assertEquals(HttpStatus.BAD_REQUEST + " \"Existem campos obrigatorios que não foram preenchidos\"", exception.getMessage());
    }

    @Test
    void deveLancarExcecaoQuandoTodosCamposVazios() {
        UsuarioDomain usuario = new UsuarioDomain();
        usuario.setUsuario(" ");
        usuario.setNome("");
        usuario.setEmail("  ");

        CampoObrigatorioException exception = assertThrows(
                CampoObrigatorioException.class,
                () -> ValidarCamposObrigatoriosRule.validarCamposObrigatorios(usuario)
        );

        assertEquals(HttpStatus.BAD_REQUEST + " \"Existem campos obrigatorios que não foram preenchidos\"", exception.getMessage());
    }

    @Test
    void naoDeveLancarExcecaoQuandoUmCampoAusente() {
        UsuarioDomain usuario = new UsuarioDomain();
        usuario.setUsuario("marcos.silva");
        usuario.setNome("Marcos Silva");
        usuario.setEmail(null);

        assertDoesNotThrow(() -> ValidarCamposObrigatoriosRule.validarCamposObrigatorios(usuario));
    }
}
