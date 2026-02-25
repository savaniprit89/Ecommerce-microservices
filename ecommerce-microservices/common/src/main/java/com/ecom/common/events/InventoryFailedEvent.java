package com.ecom.common.events;

import java.time.Instant;

public class InventoryFailedEvent {
    public Long orderId;
    public Long productId;
    public Integer quantity;
    public String reason;
    public Instant failedAt;

    public InventoryFailedEvent() {}

    public InventoryFailedEvent(Long orderId, Long productId, Integer quantity, String reason, Instant failedAt) {
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
        this.reason = reason;
        this.failedAt = failedAt;
    }
}
