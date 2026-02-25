package com.ecom.common.events;

import java.time.Instant;

public class InventoryReservedEvent {
    public Long orderId;
    public Long productId;
    public Integer quantity;
    public Instant reservedAt;

    public InventoryReservedEvent() {}

    public InventoryReservedEvent(Long orderId, Long productId, Integer quantity, Instant reservedAt) {
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
        this.reservedAt = reservedAt;
    }
}
