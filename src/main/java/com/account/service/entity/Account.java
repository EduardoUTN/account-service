package com.account.service.entity;

import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
public class Account {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank(message = "Account name mustn't be blank")
  private String username;

  private BigDecimal balance;

  public Account() {

  }

  public Account(String username, BigDecimal balance) {
    this.username = username;
    this.balance = balance;
  }

  public Long getId() {
    return id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public BigDecimal getBalance() {
    return balance;
  }

  public void setBalance(BigDecimal balance) {
    this.balance = balance;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Account account = (Account) o;

    if (id != null ? !id.equals(account.id) : account.id != null) {
      return false;
    }
    if (username != null ? !username.equals(account.username) : account.username != null) {
      return false;
    }
    return balance != null ? balance.equals(account.balance) : account.balance == null;
  }

  @Override
  public int hashCode() {
    int result = id != null ? id.hashCode() : 0;
    result = 31 * result + (username != null ? username.hashCode() : 0);
    result = 31 * result + (balance != null ? balance.hashCode() : 0);
    return result;
  }
}