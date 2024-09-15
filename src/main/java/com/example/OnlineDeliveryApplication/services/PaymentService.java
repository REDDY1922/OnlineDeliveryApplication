package com.example.OnlineDeliveryApplication.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.param.ChargeCreateParams;

@Service
public class PaymentService {
	@Autowired
    private EmailService emailService;

    @Value("${stripe.api.key}")
    private String stripeApiKey;

    public Charge chargeCard(String token, long amount, String currency, String customerEmail) throws StripeException {
        com.stripe.Stripe.apiKey = stripeApiKey;

        ChargeCreateParams params = ChargeCreateParams.builder()
                .setAmount(amount)
                .setCurrency(currency)
                .setSource(token)
                .setDescription("Order Payment")
                .build();

        Charge charge = Charge.create(params);

        // After successful payment, send a confirmation email
        String subject = "Payment Confirmation";
        String body = "Thank you for your payment. Your transaction ID is: " + charge.getId() +
                      "\nAmount: " + (amount / 100.0) + " " + currency.toUpperCase();

        emailService.sendPaymentConfirmationEmail(customerEmail, subject, body);

        return charge;
    }
}
