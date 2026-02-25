package com.ecom.order;

import com.ecom.common.events.*;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class OrderEventListener {
    private final OrderRepository repo;

    public OrderEventListener(OrderRepository repo) {
        this.repo = repo;
    }

    @KafkaListener(topics = Topics.INVENTORY_RESERVED, groupId = "order-service")
    public void onInventoryReserved(InventoryReservedEvent e) {
        repo.findById(e.orderId).ifPresent(o -> {
            o.status = OrderStatus.INVENTORY_RESERVED;
            repo.save(o);
        });
    }

    @KafkaListener(topics = Topics.INVENTORY_FAILED, groupId = "order-service")
    public void onInventoryFailed(InventoryFailedEvent e) {
        repo.findById(e.orderId).ifPresent(o -> {
            o.status = OrderStatus.INVENTORY_FAILED;
            repo.save(o);
        });
    }

    @KafkaListener(topics = Topics.PAYMENT_SUCCESS, groupId = "order-service")
    public void onPaymentSuccess(PaymentSuccessEvent e) {
        repo.findById(e.orderId).ifPresent(o -> {
            o.status = OrderStatus.PAYMENT_SUCCESS;
            repo.save(o);
        });
    }

    @KafkaListener(topics = Topics.PAYMENT_FAILED, groupId = "order-service")
    public void onPaymentFailed(PaymentFailedEvent e) {
        repo.findById(e.orderId).ifPresent(o -> {
            o.status = OrderStatus.PAYMENT_FAILED;
            repo.save(o);
        });
    }
}
