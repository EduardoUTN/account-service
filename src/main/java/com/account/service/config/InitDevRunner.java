package com.account.service.config;

import com.account.service.entity.Account;
import com.account.service.entity.Transaction;
import com.account.service.entity.dto.TransactionDTO;
import com.account.service.entity.enums.TransactionTypeEnum;
import com.account.service.repository.AccountRepository;
import com.account.service.repository.TransactionRepository;
import com.account.service.service.AccountService;
import java.math.BigDecimal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Initial Data for Development Testing
 * ONLY for testing development - must be removed for prod release
 */
@Component
public class InitDevRunner implements CommandLineRunner{

  private static final Logger LOGGER = LoggerFactory.getLogger(InitDevRunner.class);

  private AccountService accountService;

  public InitDevRunner(AccountService accountService) {
    this.accountService = accountService;
  }

  @Override
  public void run(String... args) throws Exception {
    //TODO - add init repo
    Account account = new Account("test@gmail.com", BigDecimal.ZERO);
    account = accountService.save(account);
    Transaction transaction = accountService.saveTransaction(new TransactionDTO(TransactionTypeEnum.credit.name(), BigDecimal.valueOf(1000)), account.getId());
  }
}
