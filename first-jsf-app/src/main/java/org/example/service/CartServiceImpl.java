package org.example.service;

import org.example.rest.CartResource;
import org.example.service.dto.LineItem;
import org.example.service.dto.ProductDto;

import javax.ejb.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Stateful
public class CartServiceImpl {

    private  final Map<LineItem, Integer> lineItems = new HashMap<>();

    public List<LineItem> findAll() {
        lineItems.forEach(LineItem::setQty);
        return new ArrayList<>(lineItems.keySet());
    }

    public void addToCart(ProductDto product, Integer qty) {
        LineItem lineItem = new LineItem(product, qty);
        lineItems.put(lineItem, lineItems.getOrDefault(lineItem, 0) + qty);
    }

    public void removeProduct(ProductDto product) {
        lineItems.remove(new LineItem(product, 0));
    }

}
