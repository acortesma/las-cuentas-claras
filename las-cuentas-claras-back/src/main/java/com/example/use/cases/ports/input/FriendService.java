package com.example.use.cases.ports.input;

import com.example.entities.Friend;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface FriendService {

  Flux<Friend> retrieveAllFriendsFromGroup();

  Mono<Friend> addFriendToPaymentsGroup(Friend friend);
}
