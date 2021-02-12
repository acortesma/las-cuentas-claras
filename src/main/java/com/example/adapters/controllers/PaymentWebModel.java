package com.example.adapters.controllers;

import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class PaymentWebModel {

  @JsonProperty(access = Access.READ_ONLY)
  private String id;

  private FriendWebModel payer;
  private Double amount;
  private String description;

  //  @JsonDeserialize(using = LocalDateTimeDeserializer.class)
  //  @JsonSerialize(using = LocalDateTimeSerializer.class)
  //  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private LocalDateTime date;
}
