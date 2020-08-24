package com.account.service.service;

import com.account.service.entity.Transaction;
import com.account.service.repository.TransactionRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

  private TransactionRepository transactionRepository;

  @Autowired
  public TransactionService(TransactionRepository transactionRepository) {
    this.transactionRepository = transactionRepository;
  }

  public Optional<Transaction> findById(Long id) {
    return transactionRepository.findById(id);
  }
}
