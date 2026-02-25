package com.ecom.inventory;

import jakarta.persistence.*;

@Entity
@Table(name = "inventory")
public class InventoryItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(nullable = false, unique = true)
    public Long productId;

    @Column(nullable = false)
    public Integer available;

    public InventoryItem() {}

    public InventoryItem(Long productId, Integer available) {
        this.productId = productId;
        this.available = available;
    }
}
