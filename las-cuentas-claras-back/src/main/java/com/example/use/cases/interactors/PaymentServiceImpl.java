package com.example.use.cases.interactors;

import org.springframework.stereotype.Service;
import com.example.entities.BalanceFriend;
import com.example.entities.BalanceGroup;
import com.example.entities.Payment;
import com.example.use.cases.ports.input.PaymentService;
import com.example.use.cases.ports.output.PaymentRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
@Slf4j
public class PaymentServiceImpl implements PaymentService {

  PaymentRepository paymentRepository;

  @Override
  public Flux<Payment> retrievePaymentsSortedByDate() {

    return paymentRepository.getAll().doOnNext(p -> log.info("Salida {}", p));
  }

  @Override
  public Mono<Payment> addPaymentToGroupFriend(Payment payment) {

    return paymentRepository.create(payment);
  }

  @Override
  public Flux<BalanceFriend> calculateBalanceGroupFriends() {

    return retrievePaymentsSortedByDate()
        .collectList()
        .map(BalanceGroup::new)
        .map(BalanceGroup::calculateBalanceGroupFriends)
        .flatMapMany(Flux::fromIterable);
  }
}
