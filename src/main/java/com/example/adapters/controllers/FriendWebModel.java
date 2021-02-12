package com.example.adapters.controllers;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FriendWebModel implements Serializable {

  private static final long serialVersionUID = 1L;

  private String id;
  private String name;
}
