package com.account.service.entity;

import com.account.service.entity.enums.TransactionTypeEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import org.hibernate.annotations.CreationTimestamp;

@Entity
public class Transaction {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private TransactionTypeEnum type;

  private BigDecimal amount;

  @CreationTimestamp
  private ZonedDateTime effectiveDate;

  @JsonIgnore
  @ManyToOne(fetch = FetchType.LAZY)
  private Account account;

  public Long getId() {
    return id;
  }

  public TransactionTypeEnum getType() {
    return type;
  }

  public void setType(TransactionTypeEnum type) {
    this.type = type;
  }

  public BigDecimal getAmount() {
    return amount;
  }

  public void setAmount(BigDecimal amount) {
    this.amount = amount;
  }

  public ZonedDateTime getEffectiveDate() {
    return effectiveDate;
  }

  public void setEffectiveDate(ZonedDateTime effectiveDate) {
    this.effectiveDate = effectiveDate;
  }

  public Account getAccount() {
    return account;
  }

  public void setAccount(Account account) {
    this.account = account;
  }
}