package com.example.adapters.persistence;

import org.springframework.stereotype.Component;
import com.example.entities.Friend;

@Component
class FriendPersistenceMapper {

  Friend modelToEntity(FriendModel friendPersistence) {

    return Friend.builder().id(friendPersistence.getId()).name(friendPersistence.getName()).build();
  }

  FriendModel entityToModel(Friend friend) {

    var friendModel = new FriendModel();
    friendModel.setId(friend.getId());
    friendModel.setName(friend.getName());

    return friendModel;
  }
}
