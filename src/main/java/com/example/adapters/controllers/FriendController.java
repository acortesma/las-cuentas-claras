package com.example.adapters.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.server.ServerResponse;
import com.example.use.cases.ports.input.FriendService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
  public Mono<ServerResponse> retrieveAllFriendsFromGroup() {

    log.info("Retrieve All Friends From Group");

    var friendsOfGroupFlux =
        service
            .retrieveAllFriendsFromGroup()
            .doOnNext(p -> log.info("controller {}", p))
            .map(mapperFriends::entityToWeb);

    return ServerResponse.ok().body(friendsOfGroupFlux, FriendWebModel.class);
  }

  @PostMapping
  public Mono<ServerResponse> addFriendToPaymentGroup(@RequestBody FriendWebModel friend) {

    log.info("Add friend to payment group: {}", friend);

    return Mono.just(friend)
        .map(mapperFriends::webToEntity)
        .flatMap(service::addFriendToPaymentsGroup)
        .map(mapperFriends::entityToWeb)
        .flatMap(user -> ServerResponse.ok().body(Mono.just(user), FriendWebModel.class))
        .switchIfEmpty(ServerResponse.notFound().build());
  }
}
