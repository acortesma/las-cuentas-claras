package com.example.adapters.persistence;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;

@Document
@Data
class FriendModel {

  @Id private String id;
  private String name;
}
