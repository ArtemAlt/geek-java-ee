package org.example.models;

import org.example.exc.CanNotSaveProduct;
import org.example.exc.RepositoryUpdateException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;


public class ProductRepository {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(ProductRepository.class);

    private final HashMap<Long, Product> repository = new HashMap<>();
    private final AtomicLong counter = new AtomicLong();

    public List<Product> getAll() {
        return new ArrayList<>(repository.values());
    }

    public Optional<Product> getProductById(Long id) {
        return Optional.ofNullable(repository.get(id));
    }

    public boolean deleteProductById(Long id) {
        if (repository.get(id) != null) {
            repository.remove(id);
            return true;
        } else {
            return false;
        }
    }

    public Product saveNewProduct(String title, String description, BigDecimal price) {
        log.info("In repository create new " + title);
        if (title != null && description != null && price != null) {
            Product p = new Product(
                    counter.incrementAndGet(),
                    title,
                    description,
                    price
            );
            repository.put(p.getId(), p);
            log.info("In repository saved - "+p);
            return p;
        } else {
            throw new CanNotSaveProduct();
        }
    }

    public void saveOrUpdate(Product product) {
        if (repository.containsKey(product.getId())) {
            log.info("Repository contain such id product");
            repository.replace(product.getId(), product);
        } else if(product.getName()!= null && product.getDescription() != null && product.getPrice()!= null) {
            Product p = new Product(
                    counter.incrementAndGet(),
                    product.getName(),
                    product.getDescription(),
                    product.getPrice()
            );
            repository.put(p.getId(), p);
            log.info("In repository saved - "+p);
        } else {
            throw new RepositoryUpdateException();
        }
    }

    public void save(Product p) {
        if (p.getId()==null){
            p.setId(counter.incrementAndGet());
        }
        repository.put(p.getId(), p);
    }
}
