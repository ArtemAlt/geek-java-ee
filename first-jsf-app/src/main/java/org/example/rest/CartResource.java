package org.example.rest;


import org.example.service.dto.LineItem;
import org.example.service.dto.ProductDto;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/v1/cart")
public interface CartResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    List<LineItem> findAll();


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    void addToCart(ProductDto product, Integer qty);


    @DELETE
    void removeProduct(ProductDto product);
}
