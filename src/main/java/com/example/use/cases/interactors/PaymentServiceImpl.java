package com.example.use.cases.interactors;

import java.util.Map;
import org.springframework.stereotype.Service;
import com.example.entities.Balance;
import com.example.entities.Balance2;
import com.example.entities.BalanceFriend;
import com.example.entities.BalanceGroup;
import com.example.entities.Friend;
import com.example.entities.Payment;
import com.example.use.cases.ports.input.PaymentService;
import com.example.use.cases.ports.output.PaymentRepository;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class PaymentServiceImpl implements PaymentService {

  PaymentRepository paymentRepository;

  @Override
  public Flux<Payment> retrievePaymentsSortedByDate() {

    return paymentRepository.getAll();
  }

  @Override
  public Mono<Payment> addPaymentToGroupFriend(Payment payment) {

    return paymentRepository.create(payment);
  }

  @Override
  public Mono<Map<String, Double>> calculateBalanceByEachfriend2() {

    return retrievePaymentsSortedByDate()
        .collectList()
        .map(Balance2::new)
        .map(Balance2::calculateBalanceGroupFriends);
  }

  @Override
  public Mono<Map<Friend, Double>> calculateBalanceByEachfriend() {

    return retrievePaymentsSortedByDate()
        .collectList()
        .map(Balance::new)
        .map(Balance::calculateBalance);
  }

  @Override
  public Flux<BalanceFriend> calculateBalanceGroup() {

    Flux<BalanceFriend> dfgh =
        retrievePaymentsSortedByDate()
            .collectList()
            .map(BalanceGroup::new)
            .map(BalanceGroup::calculateBalanceGroupFriends)
            .flux()
            .flatMap(e -> extracted(e));

    return dfgh;
  }

  private Flux<BalanceFriend> extracted(Map<String, Double> e) {

    return Flux.fromStream(
        e.entrySet().stream().map(entry -> new BalanceFriend(entry.getKey(), entry.getValue())));
  }

  @Override
  public Flux<BalanceFriend> calculateBalanceGroup2() {

    Flux<BalanceFriend> dfgh =
        retrievePaymentsSortedByDate()
            .collectList()
            .map(BalanceGroup::new)
            .map(BalanceGroup::calculate)
            .flatMapMany(Flux::fromIterable);

    return dfgh;
  }
}
