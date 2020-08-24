package com.account.service.service;

import com.account.service.config.InitDevRunner;
import com.account.service.entity.Account;
import com.account.service.entity.Transaction;
import com.account.service.entity.dto.TransactionDTO;
import com.account.service.entity.enums.TransactionTypeEnum;
import com.account.service.exception.AccountFundsException;
import com.account.service.exception.EntityDoesnNotExistException;
import com.account.service.repository.AccountRepository;
import com.account.service.repository.TransactionRepository;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountService implements Transactionable<Account> {

  private static final Logger LOGGER = LoggerFactory.getLogger(InitDevRunner.class);

  private AccountRepository accountRepository;
  private TransactionRepository transactionRepository;

  @Autowired
  public AccountService(AccountRepository accountRepository, TransactionRepository transactionRepository) {
    this.accountRepository = accountRepository;
    this.transactionRepository = transactionRepository;
  }

  public Iterable<Account> findAll() {
    return accountRepository.findAll();
  }

  public Optional<Account> findById(Long id) {
    return accountRepository.findById(id);
  }

  public Account save(Account account) {
    return accountRepository.save(account);
  }

  @Transactional
  public Transaction saveTransaction(TransactionDTO transactionDTO, Long accountId)
      throws AccountFundsException, EntityDoesnNotExistException {

    Account account = accountRepository.getOne(accountId);
    if (TransactionTypeEnum.credit.equals(TransactionTypeEnum.getFromName(transactionDTO.getType()))) {
      account.setBalance(account.getBalance().add(transactionDTO.getAmount()));
    } else {
      if ((account.getBalance().compareTo(transactionDTO.getAmount()) >= 0)) {
        account.setBalance(account.getBalance().add(transactionDTO.getAmount().negate()));
      } else {
        throw new AccountFundsException(accountId);
      }
    }
    Transaction transaction = new Transaction();
    transaction.setAmount(transactionDTO.getAmount());
    transaction.setType(TransactionTypeEnum.getFromName(transactionDTO.getType()));
    transaction.setAccount(account);

    return transactionRepository.save(transaction);
  }

  public List<Transaction> findAllTransactionsByAccountId(Long accountId)
      throws EntityDoesnNotExistException {
    if (accountRepository.existsById(accountId)) {
      return transactionRepository.findAllByAccount_Id(accountId);
    } else {
      throw new EntityDoesnNotExistException(Account.class.getName(), accountId);
    }
  }

  public Transaction findTransactionByIdAndAccountId(Long transactionId, Long accountId)
      throws EntityDoesnNotExistException {
    return transactionRepository.findByIdAndAccount_Id(transactionId, accountId)
        .orElseThrow(() -> new EntityDoesnNotExistException(Transaction.class.getName(), transactionId));
  }

  @Override
  public Account deposit(TransactionDTO transactionDTO) {
    return null;
  }

  @Override
  public Account withdraw(TransactionDTO transactionDTO) {
    return null;
  }
}