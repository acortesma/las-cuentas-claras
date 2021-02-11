package com.example.adapters.persistence;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface FriendReactiveMongoRepository
    extends ReactiveMongoRepository<FriendModel, String> {

  Flux<FriendModel> findAllById(String value);
}
