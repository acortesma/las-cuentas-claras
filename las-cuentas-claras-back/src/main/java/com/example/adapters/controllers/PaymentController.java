package com.example.adapters.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.entities.BalanceFriend;
import com.example.use.cases.ports.input.PaymentService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Tag(name = "Payments")
@RestController
@RequestMapping("/payments")
@AllArgsConstructor
@Slf4j
public class PaymentController {

  private final PaymentService service;
  private final PaymentWebMapper mapperPayment;

  @GetMapping
  public Flux<PaymentWebModel> retrieveAllPaymentsFromGroup() {

    log.info("Retrive payments From Group");

    return service
        .retrievePaymentsSortedByDate()
        .doOnNext(p -> log.info("Salida {}", p))
        .map(mapperPayment::entityToWeb);
  }

  @PostMapping
  public Mono<PaymentWebModel> addPaymentToGroupFriend(@RequestBody PaymentWebModel payment) {

    log.info("Payment to register: {}", payment);

    return Mono.just(payment)
        .map(mapperPayment::webToEntity)
        .flatMap(service::addPaymentToGroupFriend)
        .map(mapperPayment::entityToWeb)
        .doOnNext(p -> log.info("exit {}", p));
  }

  @GetMapping("/balance")
  public Flux<BalanceFriend> calculateBalanceGroup() {

    log.info("Obtain balance for each Friend: {}");

    return service.calculateBalanceGroupFriends().doOnNext(p -> log.info("controller {}", p));
  }
}
