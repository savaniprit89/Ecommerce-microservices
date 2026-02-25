package com.ecom.common.events;

import java.time.Instant;

public class PaymentSuccessEvent {
    public Long orderId;
    public Double amount;
    public Instant paidAt;

    public PaymentSuccessEvent() {}

    public PaymentSuccessEvent(Long orderId, Double amount, Instant paidAt) {
        this.orderId = orderId;
        this.amount = amount;
        this.paidAt = paidAt;
    }
}
