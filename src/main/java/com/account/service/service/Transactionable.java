package com.account.service.service;

import com.account.service.entity.dto.TransactionDTO;

public interface Transactionable<R> {

  R deposit(TransactionDTO transactionDTO);

  R withdraw(TransactionDTO transactionDTO);
}
