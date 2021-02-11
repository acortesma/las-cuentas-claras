package com.example.entities;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Friend {

  private String id;
  private String name;
}
