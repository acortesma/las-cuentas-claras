package com.example.adapters.controllers;

import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.entities.BalanceFriend;
import com.example.entities.Friend;
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

    return service
        .retrievePaymentsSortedByDate()
        .doOnNext(p -> log.info("controller {}", p))
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
  public Mono<Map<Friend, Double>> calculateBalanceByEachfriend() {

    return service.calculateBalanceByEachfriend().doOnNext(p -> log.info("controller {}", p));
  }

  @GetMapping("/balance2")
  public Mono<Map<String, Double>> calculateBalanceByEachfriend2() {

    return service.calculateBalanceByEachfriend2().doOnNext(p -> log.info("controller {}", p));
  }

  @GetMapping("/balanceGroup")
  public Flux<BalanceFriend> calculateBalanceGroup() {

    return service.calculateBalanceGroup().doOnNext(p -> log.info("controller {}", p));
  }

  @GetMapping("/balanceGroup2")
  public Flux<BalanceFriend> calculateBalanceGroup2() {

    return service.calculateBalanceGroup2().doOnNext(p -> log.info("controller {}", p));
  }
}
