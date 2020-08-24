package com.account.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class AccountFundsException extends Exception{

  private static final long serialVersionUID = 1L;

  public AccountFundsException(Long id) {
    super(String.format("Account id: %d does not have enough funds for this operation",	id));
  }
}
