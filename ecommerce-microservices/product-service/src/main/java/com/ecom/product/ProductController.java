package com.ecom.product;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService svc;

    public ProductController(ProductService svc) {
        this.svc = svc;
    }

    @PostMapping
    public Product create(@RequestBody Product p) {
        return svc.create(p);
    }

    @GetMapping("/{id}")
    public Product get(@PathVariable Long id) {
        return svc.getById(id);
    }

    @GetMapping
    public List<Product> list() {
        return svc.list();
    }

    @PutMapping("/{id}")
    public Product update(@PathVariable Long id, @RequestBody Product p) {
        return svc.update(id, p);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        svc.delete(id);
    }
}
