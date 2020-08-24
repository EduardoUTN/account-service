package com.account.service.entity.dto;

import com.account.service.entity.enums.TransactionTypeEnum;
import com.account.service.validation.annotation.ValueOfEnum;
import java.math.BigDecimal;
import javax.validation.constraints.Positive;

public class TransactionDTO {

  @ValueOfEnum(enumClass = TransactionTypeEnum.class)
  private String type;

  @Positive
  private BigDecimal amount;

  public TransactionDTO(String transactionTypeEnum, BigDecimal amount) {
    this.type = transactionTypeEnum;
    this.amount = amount;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public BigDecimal getAmount() {
    return amount;
  }

  public void setAmount(BigDecimal amount) {
    this.amount = amount;
  }
}