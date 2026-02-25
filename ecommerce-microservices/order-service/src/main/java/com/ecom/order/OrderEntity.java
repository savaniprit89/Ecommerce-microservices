package com.ecom.order;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "orders")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(nullable = false)
    public Long productId;

    @Column(nullable = false)
    public Integer quantity;

    @Column(nullable = false)
    public Double amount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    public OrderStatus status;

    @Column(nullable = false)
    public Instant createdAt;

    public OrderEntity() {}
}
