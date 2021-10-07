package org.example.service;

import org.example.persist.Product;
import org.example.service.dto.ProductDto;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<ProductDto> findAll();

    List<ProductDto> findByCategoryId(long categoryId);

    Optional<ProductDto> findById(long id);

    Product save(ProductDto productDto);

    void delete(long id);

    long count();
}
