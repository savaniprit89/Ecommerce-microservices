package com.ecom.notify;

import com.ecom.common.events.*;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class NotificationListener {

    @KafkaListener(topics = Topics.ORDER_CREATED, groupId = "notification-service")
    public void onOrderCreated(OrderCreatedEvent e) {
        System.out.println("[NOTIFY] Order created: " + e.orderId);
    }

    @KafkaListener(topics = Topics.PAYMENT_SUCCESS, groupId = "notification-service")
    public void onPaymentSuccess(PaymentSuccessEvent e) {
        System.out.println("[NOTIFY] Payment success for order: " + e.orderId);
    }

    @KafkaListener(topics = Topics.INVENTORY_FAILED, groupId = "notification-service")
    public void onInventoryFailed(InventoryFailedEvent e) {
        System.out.println("[NOTIFY] Inventory failed for order: " + e.orderId + " reason=" + e.reason);
    }

    @KafkaListener(topics = Topics.PAYMENT_FAILED, groupId = "notification-service")
    public void onPaymentFailed(PaymentFailedEvent e) {
        System.out.println("[NOTIFY] Payment failed for order: " + e.orderId + " reason=" + e.reason);
    }
}
