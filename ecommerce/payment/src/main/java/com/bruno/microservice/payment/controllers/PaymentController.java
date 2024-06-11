package com.bruno.microservice.payment.controllers;

import com.bruno.microservice.payment.entities.Payment;
import com.bruno.microservice.payment.services.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Payment> findAllPayment() {
        return paymentService.findAllPayments();
    }

    @PostMapping("order-id/{orderID}/card-id/{cardID}")
    @ResponseStatus(HttpStatus.CREATED)
    public Payment createNewPayment(@PathVariable UUID orderID, @PathVariable UUID cardID) {
        return paymentService.createNewPayment(orderID, cardID);
    }

    @GetMapping("/{paymentID}")
    @ResponseStatus(HttpStatus.OK)
    public Payment findPaymentById(@PathVariable UUID paymentID) {
        return paymentService.findPaymentById(paymentID);
    }

    @PutMapping("/{paymentID}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Payment updatePayment(@RequestBody Payment payment, @PathVariable UUID paymentID) {
        return paymentService.updatePayment(payment, paymentID);
    }

    @DeleteMapping("/{paymentID}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePaymentById(@PathVariable UUID paymentID) {
        paymentService.deletePaymentById(paymentID);
    }
}
