package com.account.service.entity.enums;

import java.util.stream.Stream;


/**
 * Operations available for Transactions
 */
public enum TransactionTypeEnum {
  credit,
  debit;

  public static TransactionTypeEnum getFromName(String name) {
    return Stream.of(TransactionTypeEnum.values())
        .filter(type -> type.name().equals(name))
        .findFirst()
        .orElse(null);
  }
}