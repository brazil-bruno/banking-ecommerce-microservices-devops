package com.bruno.microservice.payment.services;

import com.bruno.microservice.payment.entities.Card;
import com.bruno.microservice.payment.entities.Order;
import com.bruno.microservice.payment.entities.Payment;
import com.bruno.microservice.payment.feignclients.CardFeignClient;
import com.bruno.microservice.payment.feignclients.OrderFeignClient;
import com.bruno.microservice.payment.repositories.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;

    private final OrderFeignClient orderFeignClient;

    private final CardFeignClient cardFeignClient;

    public List<Payment> findAllPayments() {
        return paymentRepository.findAll();
    }

    public Payment createNewPayment(UUID orderID, UUID cardID) {
        Order order = orderFeignClient.findOrderById(orderID);
        Card card = cardFeignClient.findCardById(cardID);

        double amountToPay = card.getCardLimit() - order.getTotalAmount();

        card.setCardLimit(amountToPay);

        cardFeignClient.updateCard(card, card.getCardID());

        Payment entity = Payment.builder()
                .orderID(order.getOrderID())
                .cardID(card.getCardID())
                .totalPaid(order.getTotalAmount())
                .build();
        return paymentRepository.save(entity);
    }

    public Payment findPaymentById(UUID paymentID) {
        return paymentRepository.findById(paymentID).get();
    }

    public Payment updatePayment(Payment payment, UUID paymentID) {
        return paymentRepository.save(payment);
    }

    public void deletePaymentById(UUID paymentID) {
        paymentRepository.deleteById(paymentID);
    }
}
