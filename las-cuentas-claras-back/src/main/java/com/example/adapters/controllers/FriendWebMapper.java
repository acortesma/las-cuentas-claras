package com.example.adapters.controllers;

import org.springframework.stereotype.Component;
import com.example.entities.Friend;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
class FriendWebMapper {

  FriendWebModel entityToWeb(Friend friend) {

    log.info("mapperWebOut: {}", friend);

    return FriendWebModel.builder().id(friend.getId()).name(friend.getName()).build();
  }

  Friend webToEntity(FriendWebModel friendIn) {

    log.info("mapperWebIn: {}", friendIn);

    return Friend.builder().id(friendIn.getId()).name(friendIn.getName()).build();
  }
}
