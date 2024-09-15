package com.example.OnlineDeliveryApplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.OnlineDeliveryApplication.services.PaymentService;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;

@RestController
@RequestMapping("/payments")
public class PaymentController {
	@Autowired
    private PaymentService paymentService;

    @PostMapping("/charge")
    public ResponseEntity<String> chargeCard(@RequestParam String token,
                                             @RequestParam long amount,
                                             @RequestParam String currency,
                                             @RequestParam String customerEmail) {
        try {
            Charge charge = paymentService.chargeCard(token, amount, currency, customerEmail);
            return ResponseEntity.ok("Payment Successful! Transaction ID: " + charge.getId());
        } catch (StripeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Payment Failed: " + e.getMessage());
        }
    }
}
