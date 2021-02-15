package com.example.adapters.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.use.cases.ports.input.FriendService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Tag(name = "Friends")
@RestController
@RequestMapping("/friends")
@AllArgsConstructor
@Slf4j
public class FriendController {

  private final FriendService service;
  private final FriendWebMapper mapperFriends;

  @GetMapping
  public Flux<FriendWebModel> retrieveAllFriendsFromGroup() {

    log.info("Retrieve All Friends From Group");

    return service
        .retrieveAllFriendsFromGroup()
        .map(mapperFriends::entityToWeb)
        .doOnNext(p -> log.info("Salida {}", p));
  }

  @PostMapping
  public Mono<FriendWebModel> addFriendToPaymentGroup(@RequestBody FriendWebModel friend) {

    log.info("Add friend to payment group: {}", friend);

    return Mono.just(friend)
        .map(mapperFriends::webToEntity)
        .flatMap(service::addFriendToPaymentsGroup)
        .map(mapperFriends::entityToWeb);
  }
}
