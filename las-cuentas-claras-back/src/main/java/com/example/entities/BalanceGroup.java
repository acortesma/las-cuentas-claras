package com.example.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BalanceGroup {

  public BalanceGroup(List<Payment> payments) {
    this.payments = List.copyOf(payments);
  }

  private final List<Payment> payments;

  public List<BalanceFriend> calculateBalanceGroupFriends() {

    var listPaymentsFromEachFriend = groupPaymentsFromEachFriend();

    var average = calculateAverage(listPaymentsFromEachFriend);

    return calculateBalaceFromEachFriend(listPaymentsFromEachFriend, average);
  }

  private List<BalanceFriend> groupPaymentsFromEachFriend() {

    return new ArrayList<>(
        payments
            .stream()
            .collect(
                Collectors.toMap(
                    Payment::getPayer,
                    p -> BalanceFriend.of(p.getPayer().getName(), p.getAmount()),
                    (BalanceFriend payment1, BalanceFriend payment2) -> {
                      payment1.addAmount(payment2.getAmount());
                      return payment1;
                    }))
            .values());
  }

  private double calculateAverage(List<BalanceFriend> result) {
    return result.stream().mapToDouble(BalanceFriend::getAmount).average().orElse(0D);
  }

  private List<BalanceFriend> calculateBalaceFromEachFriend(
      List<BalanceFriend> result, double average) {
    return result
        .stream()
        .map(
            balanceFriend -> {
              balanceFriend.calculateBalance(average);
              return balanceFriend;
            })
        .collect(Collectors.toList());
  }
}
