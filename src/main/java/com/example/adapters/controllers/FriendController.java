package com.example.adapters.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.server.ServerResponse;
import com.example.use.cases.ports.input.FriendService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/friends")
@AllArgsConstructor
@Slf4j
public class FriendController {

  private final FriendService service;
  private final FriendWebResponseMapper mapperFriends;

  @GetMapping
  public ResponseEntity<Flux<FriendWebResponse>> retrieveModel(
      @RequestParam(required = false) final String filter) {
    log.info("Filter albums by: {}", filter);

    final Flux<FriendWebResponse> model =
        service
            .retrieveFriends(filter)
            .doOnNext(p -> log.info("controller {}", p))
            .map(mapperFriends::entityToWeb);

    return ResponseEntity.ok(model);
  }

  @GetMapping("/all")
  public Mono<ServerResponse> retrieveModel2(@RequestParam(required = false) final String filter) {
    log.info("Filter albums by: {}", filter);

    return service
        .retrieveFriends(filter)
        .doOnNext(p -> log.info("controller {}", p))
        .map(mapperFriends::entityToWeb)
        .collectList()
        .flatMap(user -> ServerResponse.ok().body(Mono.just(user), FriendWebResponse.class));
  }

  @PostMapping
  public Mono<ServerResponse> addFriendToPaymentsGroup(@RequestBody FriendWebResponse friend) {

    log.info("Usuario a crear: {}", friend);

    return Mono.just(friend)
        .map(mapperFriends::webToEntity)
        .flatMap(service::addFriendToPaymentsGroup)
        .map(mapperFriends::entityToWeb)
        .flatMap(user -> ServerResponse.ok().body(Mono.just(user), FriendWebResponse.class))
        .switchIfEmpty(ServerResponse.notFound().build());
  }
}
