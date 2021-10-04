package org.example.service;

import org.example.service.dto.LineItem;
import org.example.service.dto.ProductDto;

import javax.ejb.Stateful;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Stateful
public class CartService {

    private final Map<Long,LineItem> lineItems = new HashMap<>();

    public List<LineItem> findAll() {
        return new ArrayList<>(lineItems.values());
    }

    public void addToCart(ProductDto product, Integer qty) {
        LineItem item = new LineItem(product,qty,product.getPrice());
        lineItems.put(product.getId(),item);
    }

    public void removeProduct(ProductDto product) {
        lineItems.remove(product.getId());
    }

}
