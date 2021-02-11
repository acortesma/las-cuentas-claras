package com.example.use.cases.interactors;

import org.springframework.stereotype.Service;
import com.example.entities.Friend;
import com.example.use.cases.ports.input.FriendService;
import com.example.use.cases.ports.output.FriendRepository;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class FriendServiceImpl implements FriendService {

  private final FriendRepository friendRepository;

  public Flux<Friend> retrieveFriends(String filter) {

    return friendRepository.getModelPersistence();
  }

  @Override
  public Mono<Friend> addFriendToPaymentsGroup(Friend friend) {

    return friendRepository.create(friend);
  }
}
