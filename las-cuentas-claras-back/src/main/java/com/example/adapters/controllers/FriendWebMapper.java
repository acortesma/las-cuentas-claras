package com.example.adapters.controllers;

import org.springframework.stereotype.Component;
import com.example.entities.Friend;

@Component
class FriendWebMapper {

  FriendWebModel entityToWeb(Friend friend) {

    return FriendWebModel.builder().id(friend.getId()).name(friend.getName()).build();
  }

  Friend webToEntity(FriendWebModel friendIn) {

    return Friend.builder().id(friendIn.getId()).name(friendIn.getName()).build();
  }
}
