package com.example.use.cases.interactors;

import org.springframework.stereotype.Service;
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
}
