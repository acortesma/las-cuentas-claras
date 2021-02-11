package com.example.adapters.persistence;

import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;

@Document
@Data
class FriendModel {

  private String id;
  private String name;
}
