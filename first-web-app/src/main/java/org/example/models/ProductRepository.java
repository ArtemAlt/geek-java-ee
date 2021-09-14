package org.example.models;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;


public class ProductRepository {
    private final HashMap<Long, Product> repository = new HashMap<>();
    private AtomicLong counter=new AtomicLong();

    public List<Product> getAll() {
        return new ArrayList<>(repository.values());
    }

    public Optional<Product> getProductById(Long id) {
        return Optional.ofNullable(repository.get(id));
    }

    public String deleteProductById(Long id) {
        if (repository.get(id) != null) {
            repository.remove(id);
            return "Product with id - " + id + " removed";
        } else {
            return "No such product id - " + id;
        }
    }

    public String saveNewProduct(String title, String description, BigDecimal price) {
        if (title != null && description != null && price != null) {
            Product p = new Product.Builder()
                    .setId(counter.incrementAndGet())
                    .setTitle(title)
                    .setDescription(description)
                    .setPrice(price)
                    .build();
            repository.put(p.getId(),p);
            return "Product - "+p+" saved";
        } else {
            return "Can not create and save product";
        }

    }
}
