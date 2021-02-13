package com.example.use.cases.ports.output;

import com.example.entities.Friend;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface FriendRepository {

  Flux<Friend> getAll();

  Mono<Friend> create(Friend friend);
}
