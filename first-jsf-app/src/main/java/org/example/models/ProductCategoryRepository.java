package org.example.models;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@ApplicationScoped
@Named
public class ProductCategoryRepository {

    private final Map<Long, ProductCategory> productCategoryMap = new HashMap<>();

    private AtomicLong identity = new AtomicLong();

    @PostConstruct
    public void init() {
        this.save(new ProductCategory(null, "Категория 1"));
        this.save(new ProductCategory(null, "Категория 2"));
        this.save(new ProductCategory(null, "Категория 3"));
        this.save(new ProductCategory(null, "Категория 4"));
    }

    public List<ProductCategory> findAll() {
        return new ArrayList<>(productCategoryMap.values());
    }

    public Optional<ProductCategory> findById(long id) {
        return Optional.ofNullable(productCategoryMap.get(id));
    }

    public ProductCategory save(ProductCategory category) {
        if (category.getId() == null) {
            category.setId(identity.incrementAndGet());
        }
        return productCategoryMap.put(category.getId(), category);
    }

    public void delete(long id) {
        productCategoryMap.remove(id);
    }
}
