package com.br.fiap.domain.rules;

import com.br.fiap.domain.exception.FieldRequiredException;
import com.br.fiap.domain.model.UserDomain;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static com.br.fiap.util.mocks.UsuarioDomainMocks.getUsuarioDomain;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class ValidarCamposObrigatoriosRuleTest {

    @Test
    void deveValidarCamposObrigatoriosQuandoTodosPreenchidos() {
        UserDomain usuario = getUsuarioDomain();
        assertDoesNotThrow(() -> ValidarCamposObrigatoriosRule.validarCamposObrigatorios(usuario));
    }

    @Test
    void deveLancarExcecaoQuandoTodosCamposNulos() {
        UserDomain user = new UserDomain();

        FieldRequiredException exception = assertThrows(
                FieldRequiredException.class,
                () -> ValidarCamposObrigatoriosRule.validarCamposObrigatorios(user)
        );

        assertEquals(HttpStatus.BAD_REQUEST + " \"Existem campos obrigatorios que não foram preenchidos\"", exception.getMessage());
    }

    @Test
    void deveLancarExcecaoQuandoTodosCamposVazios() {
        UserDomain usuario = new UserDomain();
        usuario.setUsername(" ");
        usuario.setName("");
        usuario.setEmail("  ");

        FieldRequiredException exception = assertThrows(
                FieldRequiredException.class,
                () -> ValidarCamposObrigatoriosRule.validarCamposObrigatorios(usuario)
        );

        assertEquals(HttpStatus.BAD_REQUEST + " \"Existem campos obrigatorios que não foram preenchidos\"", exception.getMessage());
    }

    @Test
    void naoDeveLancarExcecaoQuandoUmCampoAusente() {
        UserDomain usuario = new UserDomain();
        usuario.setUsername("marcos.silva");
        usuario.setName("Marcos Silva");
        usuario.setEmail(null);

        assertDoesNotThrow(() -> ValidarCamposObrigatoriosRule.validarCamposObrigatorios(usuario));
    }
}
