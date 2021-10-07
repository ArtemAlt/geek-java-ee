package org.example.service;


import org.example.persist.*;
import org.example.rest.ProductResource;
import org.example.service.dto.ProductDto;

import javax.ejb.*;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Stateless
@Local({ProductService.class, ProductResource.class})
public class ProductServiceImpl implements ProductService, ProductResource {

    @EJB
    private ProductRepository productRepository;

    @EJB
    private CategoryRepository categoryRepository;

    @EJB
    private BrandRepository brandRepository;

    public List<ProductDto> findAll() {
        return productRepository.findAll().stream()
                .map(ProductServiceImpl::convert)
                .collect(Collectors.toList());
    }

    public List<ProductDto> findByCategoryId(long categoryId) {
        return productRepository.findByCategoryId(categoryId).stream()
                .map(ProductServiceImpl::convert)
                .collect(Collectors.toList());
    }

    public Optional<ProductDto> findById(long id) {
        return productRepository.findById(id)
                .map(ProductServiceImpl::convert);
    }

    public ProductDto findByIdOrException(long id) {
        return findById(id)
                .orElseThrow(() -> new RuntimeException("Not found"));
    }

    @TransactionAttribute
    public Product save(ProductDto productDto) {
        Product product = new Product(
                productDto.getId(),
                productDto.getName(),
                productDto.getPrice(),
                categoryRepository.getReference(productDto.getCategoryId()),
                brandRepository.getReference(productDto.getBrandId())
        );
        return productRepository.save(product);
    }

    public ProductDto update(ProductDto productDto) {
        if (productDto.getId() == null) {
            throw new RuntimeException("Id shouldn't be null for new Product");
        }
        Product saved = this.save(productDto);
        return new ProductDto(saved.getId(),
                saved.getName(),
                saved.getPrice(),
                saved.getCategory().getId(),
                saved.getCategory().getName(),
                saved.getBrand().getId(),
                saved.getBrand().getName());
    }

    public ProductDto insert(ProductDto productDto) {
        if (productDto.getId() != null) {
            throw new RuntimeException("Id should be null for new Product");
        }
        Product saved = this.save(productDto);
        return new ProductDto(saved.getId(),
                saved.getName(),
                saved.getPrice(),
                saved.getCategory().getId(),
                saved.getCategory().getName(),
                saved.getBrand().getId(),
                saved.getBrand().getName());
    }

    @TransactionAttribute
    public void delete(long id) {
        productRepository.delete(id);
    }

    public long count() {
        return productRepository.count();
    }

    private static ProductDto convert(Product prod) {
        return new ProductDto(
                prod.getId(),
                prod.getName(),
                prod.getPrice(),
                prod.getCategory() != null ? prod.getCategory().getId() : null,
                prod.getCategory() != null ? prod.getCategory().getName() : null,
                prod.getBrand().getId() != null ? prod.getBrand().getId() : null,
                prod.getBrand().getName() != null ? prod.getName() : null
        );
    }


}