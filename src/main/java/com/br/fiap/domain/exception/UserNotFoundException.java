package com.br.fiap.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class UserNotFoundException extends ResponseStatusException {

  public UserNotFoundException(String mensagem) {
    super(HttpStatus.NOT_FOUND, mensagem);
  }
}