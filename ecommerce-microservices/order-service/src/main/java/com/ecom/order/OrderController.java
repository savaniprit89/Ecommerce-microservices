package com.ecom.order;

import com.ecom.common.events.OrderCreatedEvent;
import com.ecom.common.events.Topics;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderRepository repo;
    private final KafkaTemplate<String, Object> kafka;

    public OrderController(OrderRepository repo, KafkaTemplate<String, Object> kafka) {
        this.repo = repo;
        this.kafka = kafka;
    }

    @PostMapping
    public OrderEntity create(@RequestBody OrderRequest req) {
        OrderEntity o = new OrderEntity();
        o.productId = req.productId;
        o.quantity = req.quantity;
        o.amount = req.amount;
        o.status = OrderStatus.CREATED;
        o.createdAt = Instant.now();

        OrderEntity saved = repo.save(o);

        kafka.send(Topics.ORDER_CREATED,
                new OrderCreatedEvent(saved.id, saved.productId, saved.quantity, saved.amount, saved.createdAt));

        return saved;
    }

    @GetMapping
    public List<OrderEntity> list() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public OrderEntity get(@PathVariable Long id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("Order not found: " + id));
    }
}
