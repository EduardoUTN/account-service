package com.account.service.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EntityDoesnNotExistException extends Exception {


  private static final long serialVersionUID = 1L;

  public EntityDoesnNotExistException(String className, Long id) {
    super(String.format("'%s' id '%d' does not exist", className, id));
  }
}
