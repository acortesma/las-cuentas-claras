package com.example.adapters.persistence;

import java.time.LocalDateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;

@Document
@Data
public class PaymentModel {

  @Id private String id;
  private FriendModel payer;
  private Double amount;
  private String description;
  private LocalDateTime date;
}
