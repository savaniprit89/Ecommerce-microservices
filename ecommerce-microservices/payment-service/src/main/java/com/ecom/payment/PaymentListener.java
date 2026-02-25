package com.ecom.payment;

import java.time.Instant;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.ecom.common.events.InventoryReservedEvent;
import com.ecom.common.events.PaymentSuccessEvent;
import com.ecom.common.events.Topics;

@Component
public class PaymentListener {
    private final KafkaTemplate<String, Object> kafka;

    public PaymentListener(KafkaTemplate<String, Object> kafka) {
        this.kafka = kafka;
    }

    @KafkaListener(topics = Topics.INVENTORY_RESERVED, groupId = "payment-service")
    public void onInventoryReserved(InventoryReservedEvent e) {
        //to do left to integrate stripe
        kafka.send(Topics.PAYMENT_SUCCESS, new PaymentSuccessEvent(e.orderId, 0.0, Instant.now()));
    }
}
