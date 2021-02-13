package com.example.entities;

import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Balance2 {

  public Balance2(List<Payment> payments) {
    this.payments =
        List.copyOf(payments)
            .stream()
            .map(p -> new FriendBalance(p.getPayer().getName(), p.getAmount()))
            .collect(Collectors.toList());
    this.averagePayment = this.calculateAveragePayment(this.payments);
  }

  private final List<FriendBalance> payments;

  private final double averagePayment;

  private double calculateAveragePayment(List<FriendBalance> payments) {
    var average =
        payments.stream().mapToDouble(FriendBalance::getAmount).sum()
            / payments.stream().map(FriendBalance::getName).distinct().count();

    log.info("average: {}", average);
    return average;
  }

  public Map<String, Double> calculateBalanceGroupFriends() {

    return payments
        .stream()
        .collect(
            Collectors.collectingAndThen(
                sumPaymensOfEachFriend(), this::calculateBalanceOfEachfriend));
  }

  private Collector<FriendBalance, ?, Map<String, Double>> sumPaymensOfEachFriend() {
    return Collectors.toMap(
        FriendBalance::getName,
        FriendBalance::getAmount,
        (payment1, payment2) -> {
          log.info("duplicate key found: {} - {}", payment1, payment2);
          return payment1 + payment2;
        });
  }

  private Map<String, Double> calculateBalanceOfEachfriend(Map<String, Double> map) {
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
