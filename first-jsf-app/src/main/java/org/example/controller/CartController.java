package org.example.controller;


import org.example.service.CartService;
import org.example.service.dto.LineItem;
import org.example.service.dto.ProductDto;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@SessionScoped
@Named
public class CartController implements Serializable {

    @EJB
    private CartService cartService;

    public List<LineItem> findAll() {
        return cartService.findAll();
    }

    public void addToCart(ProductDto product) {
        cartService.addToCart(product, 1);
    }

    public void deleteProduct(ProductDto productDto){
        cartService.removeProduct(productDto);
    }
}
