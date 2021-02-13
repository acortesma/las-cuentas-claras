package com.example.entities;

import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Balance {

  public Balance(List<Payment> payments) {
    this.payments = List.copyOf(payments);
    this.averagePayment = this.calculateAveragePayment(this.payments);
  }

  private final List<Payment> payments;

  private final double averagePayment;

  private double calculateAveragePayment(List<Payment> payments) {
    var average =
        payments.stream().mapToDouble(Payment::getAmount).sum()
            / payments.stream().map(Payment::getPayer).distinct().count();

    log.info("average: {}", average);
    return average;
  }

  public Map<Friend, Double> calculateBalance() {

    return payments
        .stream()
        .collect(
            Collectors.collectingAndThen(
                sumPaymensByEachFriend(), this::calculateBalanceByEachfriend));
  }

  private Collector<Payment, ?, Map<Friend, Double>> sumPaymensByEachFriend() {
    return Collectors.toMap(
        Payment::getPayer,
        Payment::getAmount,
        (payment1, payment2) -> {
          log.info("duplicate key found: {} - {}", payment1, payment2);
          return payment1 + payment2;
        });
  }

  private Map<Friend, Double> calculateBalanceByEachfriend(Map<Friend, Double> map) {
    return map.entrySet()
        .stream()
        .map(p -> Map.entry(p.getKey(), averagePayment - p.getValue()))
        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
  }

  class FriendBalance {

    public FriendBalance(String name, Double amount) {
      this.name = name;
      this.amount = amount;
    }

    private String name;
    private Double amount;

    public String getName() {
      return name;
    }

    public Double getAmount() {
      return amount;
    }
  }
}
