package com.example.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BalanceGroup {

  public BalanceGroup(List<Payment> payments) {
    this.payments =
        List.copyOf(payments)
            .stream()
            .map(p -> new BalanceFriend(p.getPayer().getName(), p.getAmount()))
            .collect(Collectors.toList());
    this.averagePayment = this.calculateAveragePayment(this.payments);
  }

  private final List<BalanceFriend> payments;

  private final double averagePayment;

  private double calculateAveragePayment(List<BalanceFriend> payments) {
    var average =
        payments.stream().mapToDouble(BalanceFriend::getAmount).sum()
            / payments.stream().map(BalanceFriend::getName).distinct().count();

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

  private Collector<BalanceFriend, ?, Map<String, Double>> sumPaymensOfEachFriend() {
    return Collectors.toMap(
        BalanceFriend::getName,
        BalanceFriend::getAmount,
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

  public List<BalanceFriend> calculate() {

    var listPaymentsFromEachFriend = groupPaymentsFromEachFriend();

    var average = calculateAverage(listPaymentsFromEachFriend);

    var listBalaceFromEachFriend =
        calculateBalaceFromEachFriend(listPaymentsFromEachFriend, average);

    return listBalaceFromEachFriend;
  }

  private List<BalanceFriend> groupPaymentsFromEachFriend() {
    List<BalanceFriend> result =
        new ArrayList<>(
            payments
                .stream()
                .collect(
                    Collectors.toMap(
                        BalanceFriend::getName,
                        Function.identity(),
                        (BalanceFriend payment1, BalanceFriend payment2) -> {
                          payment1.addAmount(payment2.getAmount());
                          return payment1;
                        }))
                .values());
    return result;
  }

  private double calculateAverage(List<BalanceFriend> result) {
    var average = result.stream().mapToDouble(BalanceFriend::getAmount).average().getAsDouble();
    return average;
  }

  private List<BalanceFriend> calculateBalaceFromEachFriend(
      List<BalanceFriend> result, double average) {
    var result2 =
        result
            .stream()
            .map(
                balanceFriend -> {
                  balanceFriend.calculateBalance(average);
                  return balanceFriend;
                })
            .collect(Collectors.toList());
    return result2;
  }
}
