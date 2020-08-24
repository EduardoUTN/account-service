package com.account.service.repository;

import com.account.service.entity.Transaction;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

  List<Transaction> findAllByAccount_Id(Long id);

  Optional<Transaction> findByIdAndAccount_Id(Long transactionId, Long accountId);
}
