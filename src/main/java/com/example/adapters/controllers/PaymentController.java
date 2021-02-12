package com.example.adapters.controllers;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.BodyInserters.fromValue;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.server.ServerResponse;
import com.example.use.cases.ports.input.PaymentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Tag(name = "Payments")
@RestController
@RequestMapping("/payments")
@AllArgsConstructor
@Slf4j
public class PaymentController {

  private final PaymentService service;
  private final PaymentWebMapper mapperPayment;

  
  @Operation(
      description = "Retrieve all payments from froup.",
      responses = {
          @ApiResponse(responseCode = "200", description = "List of payments found.",
              content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, 
                array = @ArraySchema(schema = @Schema(implementation = PaymentWebModel.class))))
      }
  )
  @GetMapping
  public Mono<ServerResponse> retrieveAllPaymentsFromGroup() {

    var paymentsOfGroupFlux =
        service
            .retrievePaymentsSortedByDate()
            .doOnNext(p -> log.info("controller {}", p))
            .map(mapperPayment::entityToWeb);

    return ServerResponse.ok()
//        .contentType(APPLICATION_JSON)
        .body(paymentsOfGroupFlux, PaymentWebModel.class);
  }

  
  @Operation(
      description = "Add Payment To Group Friend.",
      responses = {
          @ApiResponse(responseCode = "200", description = "Registered Payment.", 
              content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = PaymentWebModel.class)))
      }
  )
  @PostMapping
  public Mono<ServerResponse> addPaymentToGroupFriend(@RequestBody PaymentWebModel payment) {

    log.info("Payment to register: {}", payment);

    return Mono.just(payment)
        .map(mapperPayment::webToEntity)
        .flatMap(service::addPaymentToGroupFriend)
        .map(mapperPayment::entityToWeb)
        .doOnNext(p -> log.info("exit {}", p))
        .flatMap(
            registedPayment ->
                ServerResponse.ok().contentType(APPLICATION_JSON).body(fromValue(registedPayment)));
  }
  
  
}
