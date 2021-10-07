package org.example.service;

import org.example.service.dto.LineItem;
import org.example.service.dto.ProductDto;


import java.util.List;

public interface CartService  {

    List<LineItem> findAll();

    void addToCart(ProductDto product, Integer qty) ;

    void removeProduct(ProductDto product);
}
