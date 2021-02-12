package com.example.adapters.persistence;

import org.springframework.stereotype.Component;
import com.example.entities.Payment;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@AllArgsConstructor
public class PaymentPersistenceMapper {

  FriendPersistenceMapper friendMapper;

  Payment modelToEntity(PaymentModel paymentPersistence) {

    log.info("mapperPersistence {}", paymentPersistence);

    return Payment.builder()
        .id(paymentPersistence.getId())
        .payer(friendMapper.modelToEntity(paymentPersistence.getPayer()))
        .amount(paymentPersistence.getAmount())
        .description(paymentPersistence.getDescription())
        .date(paymentPersistence.getDate())
        .build();
  }

  PaymentModel entityToModel(Payment payment) {

    var paymentModel = new PaymentModel();
    paymentModel.setId(payment.getId());
    paymentModel.setPayer(friendMapper.entityToModel(payment.getPayer()));
    paymentModel.setAmount(payment.getAmount());
    paymentModel.setDescription(payment.getDescription());
    paymentModel.setDate(payment.getDate());

    return paymentModel;
  }
}
