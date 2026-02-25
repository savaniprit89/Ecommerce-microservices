package com.ecom.common.events;

import java.time.Instant;

public class PaymentFailedEvent {
    public Long orderId;
    public Double amount;
    public String reason;
    public Instant failedAt;

    public PaymentFailedEvent() {}

    public PaymentFailedEvent(Long orderId, Double amount, String reason, Instant failedAt) {
        this.orderId = orderId;
        this.amount = amount;
        this.reason = reason;
        this.failedAt = failedAt;
    }
}
