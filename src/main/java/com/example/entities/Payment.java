package com.example.entities;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Payment {

  private String id;
  private Friend payer;
  private Double amount;
  private String description;
  private LocalDateTime date;
}
