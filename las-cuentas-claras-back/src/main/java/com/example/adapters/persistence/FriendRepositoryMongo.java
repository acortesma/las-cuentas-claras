package com.example.adapters.persistence;

import org.springframework.stereotype.Component;
import com.example.entities.Friend;
import com.example.use.cases.ports.output.FriendRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@AllArgsConstructor
@Slf4j
public class FriendRepositoryMongo implements FriendRepository {

  private FriendPersistenceMapper friendMapper;
  private FriendReactiveMongoRepository friendRepository;

  public Flux<Friend> getAll() {

    return friendRepository
        .findAll()
        .doOnNext(p -> log.info("Salida {}", p))
        .map(friendMapper::modelToEntity);
  }

  @Override
  public Mono<Friend> create(Friend friend) {

    return friendRepository
        .save(friendMapper.entityToModel(friend))
        .map(friendMapper::modelToEntity);
  }
}
