package com.example.use.cases.ports.input;

import com.example.entities.BalanceFriend;
import com.example.entities.Payment;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PaymentService {

  Flux<Payment> retrievePaymentsSortedByDate();

  Mono<Payment> addPaymentToGroupFriend(Payment payment);

  Flux<BalanceFriend> calculateBalanceGroupFriends();
}
