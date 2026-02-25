package com.ecom.product;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository repo;

    public ProductService(ProductRepository repo) {
        this.repo = repo;
    }

    public Product create(Product p) {
        return repo.save(p);
    }

    @Cacheable(value = "productById", key = "#id")
    public Product getById(Long id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("Product not found: " + id));
    }

    @Cacheable(value = "productList")
    public List<Product> list() {
        return repo.findAll();
    }

    @CachePut(value = "productById", key = "#id")
    @CacheEvict(value = "productList", allEntries = true)
    public Product update(Long id, Product p) {
        Product existing = getById(id);
        existing.name = p.name;
        existing.description = p.description;
        existing.price = p.price;
        return repo.save(existing);
    }

    @CacheEvict(value = {"productById", "productList"}, allEntries = true)
    public void delete(Long id) {
        repo.deleteById(id);
    }
}
