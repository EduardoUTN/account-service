package com.account.service.controller;

import com.account.service.entity.Account;
import com.account.service.entity.Transaction;
import com.account.service.entity.dto.TransactionDTO;
import com.account.service.exception.AccountFundsException;
import com.account.service.exception.EntityDoesnNotExistException;
import com.account.service.service.AccountService;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/account")
public class AccountController {

  private AccountService accountService;

  @Autowired
  public AccountController(AccountService accountService) {
    this.accountService = accountService;
  }

  @GetMapping
  public ResponseEntity<?> getAllAccounts() {
    return ResponseEntity.ok(accountService.findAll());
  }

  @GetMapping(value = "/{id}")
  public ResponseEntity<?> getProductById(@PathVariable Long id) {
    Optional<Account> account = accountService.findById(id);
    return ResponseEntity.ok(account);
  }

  @GetMapping(value = "/{id}/transaction/history")
  public ResponseEntity<?> getTransactionsByAccountId(@PathVariable Long id)
      throws EntityDoesnNotExistException {
    List<Transaction> transactions = accountService.findAllTransactionsByAccountId(id);
    return ResponseEntity.ok(transactions);
  }

  @GetMapping(value = "/{id}/transaction/{transactionId}")
  public ResponseEntity<?> getTransactionsByAccountId(@PathVariable Long id, @PathVariable Long transactionId)
      throws EntityDoesnNotExistException {
    Transaction transaction = accountService.findTransactionByIdAndAccountId(id, transactionId);
    return ResponseEntity.ok(transaction);
  }

  @PostMapping(value = "/{id}/transaction")
  public ResponseEntity<?> saveTransaction(@PathVariable Long id, @RequestBody @Valid TransactionDTO transactionDTO)
      throws AccountFundsException, EntityDoesnNotExistException {
    Transaction transaction = accountService.saveTransaction(transactionDTO, id);
    return new ResponseEntity<>(transaction, HttpStatus.CREATED);
  }


}