package com.example.use.cases.interactors;

import org.springframework.stereotype.Service;
import com.example.entities.Friend;
import com.example.use.cases.ports.input.FriendService;
import com.example.use.cases.ports.output.FriendRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
@Slf4j
public class FriendServiceImpl implements FriendService {

  private final FriendRepository friendRepository;

  public Flux<Friend> retrieveAllFriendsFromGroup() {

    return friendRepository.getAll().doOnNext(p -> log.info("Salida {}", p));
  }

  @Override
  public Mono<Friend> addFriendToPaymentsGroup(Friend friend) {

    return friendRepository.create(friend);
  }
}
