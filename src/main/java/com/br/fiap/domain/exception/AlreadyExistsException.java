package com.br.fiap.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class AlreadyExistsException extends ResponseStatusException {

  public AlreadyExistsException(String mensagem) {
    super(HttpStatus.CONFLICT, mensagem);
  }
}