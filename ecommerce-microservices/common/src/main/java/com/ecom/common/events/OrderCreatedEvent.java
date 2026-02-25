package com.ecom.common.events;

import java.time.Instant;

public class OrderCreatedEvent {
    public Long orderId;
    public Long productId;
    public Integer quantity;
    public Double amount;
    public Instant createdAt;

    public OrderCreatedEvent() {}

    public OrderCreatedEvent(Long orderId, Long productId, Integer quantity, Double amount, Instant createdAt) {
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
        this.amount = amount;
        this.createdAt = createdAt;
    }
}
