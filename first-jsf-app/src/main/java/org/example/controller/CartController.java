package org.example.controller;

import org.example.service.CartServiceImpl;
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
    private CartServiceImpl cartServiceImpl;

    public List<LineItem> findAll() {
        return cartServiceImpl.findAll();
    }

    public void addToCart(ProductDto product) {
        cartServiceImpl.addToCart(product, 1);
    }

    public void removeFromCart(ProductDto product) {
        cartServiceImpl.removeProduct(product);
    }
}
