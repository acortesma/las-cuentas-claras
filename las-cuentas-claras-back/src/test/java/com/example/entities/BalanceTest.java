package com.example.entities;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class BalanceTest {

  @Test
  public void balanceGroup() {
    List<Payment> payments =
        List.of(
            Payment.builder().payer(Friend.builder().name("Pedro").build()).amount(50D).build(),
            Payment.builder().payer(Friend.builder().name("Maria").build()).amount(100D).build(),
            Payment.builder().payer(Friend.builder().name("Maria").build()).amount(150D).build(),
            Payment.builder().payer(Friend.builder().name("Esther").build()).amount(0D).build());
    var balance = new BalanceGroup(payments);

    var cuentas2 = balance.calculateBalanceGroupFriends();
    cuentas2.toString();

    var balanceMaria = new BalanceFriend("Maria", 250d);
    balanceMaria.calculateBalance(100d);
    var balancePedro = new BalanceFriend("Pedro", 50d);
    balancePedro.calculateBalance(100d);
    var balanceEsther = new BalanceFriend("Esther", 0d);
    balanceEsther.calculateBalance(100d);

    assertThat(cuentas2, containsInAnyOrder(balanceEsther, balancePedro, balanceMaria));
  }
}
