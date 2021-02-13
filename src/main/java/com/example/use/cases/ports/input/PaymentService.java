package com.example.use.cases.ports.input;

import java.util.Map;
import com.example.entities.BalanceFriend;
import com.example.entities.Friend;
import com.example.entities.Payment;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PaymentService {

  Flux<Payment> retrievePaymentsSortedByDate();

  Mono<Payment> addPaymentToGroupFriend(Payment payment);

  Mono<Map<Friend, Double>> calculateBalanceByEachfriend();

  Mono<Map<String, Double>> calculateBalanceByEachfriend2();

  Flux<BalanceFriend> calculateBalanceGroup();

  Flux<BalanceFriend> calculateBalanceGroup2();
}
