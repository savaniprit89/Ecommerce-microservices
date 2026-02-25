package com.ecom.inventory;

import com.ecom.common.events.*;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class InventoryListener {
    private final InventoryRepository repo;
    private final KafkaTemplate<String, Object> kafka;

    public InventoryListener(InventoryRepository repo, KafkaTemplate<String, Object> kafka) {
        this.repo = repo;
        this.kafka = kafka;
    }

    @KafkaListener(topics = Topics.ORDER_CREATED, groupId = "inventory-service")
    public void onOrderCreated(OrderCreatedEvent e) {
        InventoryItem item = repo.findByProductId(e.productId)
                .orElseGet(() -> repo.save(new InventoryItem(e.productId, 10)));

        if (item.available >= e.quantity) {
            item.available -= e.quantity;
            repo.save(item);
            kafka.send(Topics.INVENTORY_RESERVED,
                    new InventoryReservedEvent(e.orderId, e.productId, e.quantity, Instant.now()));
        } else {
            kafka.send(Topics.INVENTORY_FAILED,
                    new InventoryFailedEvent(e.orderId, e.productId, e.quantity, "INSUFFICIENT_STOCK", Instant.now()));
        }
    }
}
