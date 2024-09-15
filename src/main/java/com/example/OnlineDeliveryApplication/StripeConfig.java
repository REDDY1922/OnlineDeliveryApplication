package com.example.OnlineDeliveryApplication;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.stripe.Stripe;

@Configuration
public class StripeConfig {
	@Value("${stripe.api.key}")
    private String stripeApiKey;

   
    public StripeConfig() {
        Stripe.apiKey = stripeApiKey;
    }
}
