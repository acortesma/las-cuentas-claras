package com.example.adapters.persistence;

import org.springframework.stereotype.Component;
import com.example.entities.Payment;
import com.example.use.cases.ports.output.PaymentRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@AllArgsConstructor
@Slf4j
public class PaymentRepositoryMongo implements PaymentRepository {

  private PaymentPersistenceMapper paymentMapper;
  private PaymentReactiveMongoRepository paymentRepository;

  @Override
  public Flux<Payment> getAll() {
    return paymentRepository
        .findAllByOrderByDateDesc()
        .doOnNext(p -> log.info("Salida {}", p))
        .map(paymentMapper::modelToEntity);
  }

  @Override
  public Mono<Payment> create(Payment payment) {

    log.info("paymentRepository:{}", payment);

    return paymentRepository
        .save(paymentMapper.entityToModel(payment))
        .map(paymentMapper::modelToEntity);
  }
}
