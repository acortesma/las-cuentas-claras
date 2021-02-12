package com.example.adapters.controllers;

import org.springframework.stereotype.Component;
import com.example.entities.Payment;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@AllArgsConstructor
class PaymentWebMapper {

  private final FriendWebMapper friendWebMapper;

  PaymentWebModel entityToWeb(Payment payment) {

    log.info("mapperWebOut: {}", payment);

    return PaymentWebModel.builder()
        .id(payment.getId())
        .payer(friendWebMapper.entityToWeb(payment.getPayer()))
        .amount(payment.getAmount())
        .description(payment.getDescription())
        .date(payment.getDate())
        .build();
  }

  Payment webToEntity(PaymentWebModel paymentWebModel) {

    log.info("mapperWebIn: {}", paymentWebModel);

    return Payment.builder()
        .id(paymentWebModel.getId())
        .payer(friendWebMapper.webToEntity(paymentWebModel.getPayer()))
        .amount(paymentWebModel.getAmount())
        .description(paymentWebModel.getDescription())
        .date(paymentWebModel.getDate())
        .build();
  }
}
