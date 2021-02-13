package com.example.entities;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class BalanceTest {

  @Test
  public void balance() {
    List<Payment> payments =
        List.of(
            Payment.builder().payer(Friend.builder().name("Pedro").build()).amount(50D).build(),
            Payment.builder().payer(Friend.builder().name("Maria").build()).amount(100D).build(),
            Payment.builder().payer(Friend.builder().name("Maria").build()).amount(150D).build(),
            Payment.builder().payer(Friend.builder().name("Esther").build()).amount(0D).build());
    var balance = new Balance(payments);

    //   var cuentas =  balance.calculateBalance();
    //
    //   cuentas.toString();

    var cuentas2 = balance.calculateBalance();
    cuentas2.toString();
  }

  @Test
  public void balanceGroup() {
    List<Payment> payments =
        List.of(
            Payment.builder().payer(Friend.builder().name("Pedro").build()).amount(50D).build(),
            Payment.builder().payer(Friend.builder().name("Maria").build()).amount(100D).build(),
            Payment.builder().payer(Friend.builder().name("Maria").build()).amount(150D).build(),
            Payment.builder().payer(Friend.builder().name("Esther").build()).amount(0D).build());
    var balance = new BalanceGroup(payments);

    //   var cuentas =  balance.calculateBalance();
    //
    //   cuentas.toString();

    var cuentas2 = balance.calculate();
    cuentas2.toString();
  }
}
