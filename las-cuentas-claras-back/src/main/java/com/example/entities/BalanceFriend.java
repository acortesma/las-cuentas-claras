package com.example.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BalanceFriend {

  private final String name;
  private Double amount;
  private Double balance;

  public BalanceFriend(String name, Double amount) {
    this.name = name;
    this.amount = amount;
  }
  
  public static BalanceFriend of (String name, Double amount) {
    
    return new BalanceFriend(name, amount);
  }

  public void addAmount(Double amount) {
    this.amount += amount;
  }

  public void calculateBalance(Double averagePayments) {

    this.balance = averagePayments - amount;
  }
}
