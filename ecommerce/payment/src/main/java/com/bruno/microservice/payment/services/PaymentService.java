package com.bruno.microservice.payment.services;

import com.bruno.microservice.payment.entities.Payment;
import com.bruno.microservice.payment.repositories.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;

    public List<Payment> findAllPayments() {
        return paymentRepository.findAll();
    }

    public Payment createNewPayment(Payment payment) {
        return paymentRepository.save(payment);
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
