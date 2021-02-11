package com.example.adapters.controllers;

import org.springframework.stereotype.Component;
import com.example.entities.Friend;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
class FriendWebResponseMapper {

  FriendWebResponse entityToWeb(Friend model) {

    log.info("mapperWeb: {}", model);

    return FriendWebResponse.builder().id(model.getId()).name(model.getName()).build();
  }

  Friend webToEntity(FriendWebResponse friendIn) {

    return Friend.builder().id(friendIn.getId()).name(friendIn.getName()).build();
  }
}
