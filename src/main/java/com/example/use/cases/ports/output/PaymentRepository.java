package com.example.use.cases.ports.output;

import com.example.entities.Payment;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PaymentRepository {

  Flux<Payment> getAll();

  Mono<Payment> create(Payment payment);
}
