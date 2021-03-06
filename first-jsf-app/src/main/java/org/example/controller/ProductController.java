package org.example.controller;

import org.example.persist.Brand;
import org.example.persist.BrandRepository;
import org.example.persist.Category;
import org.example.persist.CategoryRepository;
import org.example.service.ProductService;
import org.example.service.dto.ProductDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.SessionScoped;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.List;

@SessionScoped
@Named
public class ProductController implements Serializable {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Inject
    private ProductService productService;

    @Inject
    private CategoryRepository categoryRepository;

    @Inject
    private BrandRepository brandRepository;

    @Inject
    private HttpServletRequest request;

    private List<ProductDto> products;

    private List<Category> categories;

    private List<Brand> brands;

    private ProductDto product;

    public void preloadData(ComponentSystemEvent componentSystemEvent) {
        logger.info("categoryId param: {}", request.getParameter("categoryId"));
        this.products = productService.findAll();
        this.categories = categoryRepository.findAll();
        this.brands = brandRepository.findAll();
    }

    public ProductDto getProduct() {
        return product;
    }

    public void setProduct(ProductDto product) {
        this.product = product;
    }

    public List<ProductDto> findAll() {
        return products;
    }

    public String editProduct(ProductDto product) {
        this.product = product;
        return "/product_form.xhtml?faces-redirect=true";
    }

    public String addProduct() {
        this.product = new ProductDto();
        return "/product_form.xhtml?faces-redirect=true";
    }

    public String saveProduct() {
        productService.save(product);
        return "/product.xhtml?faces-redirect=true";
    }

    public void deleteProduct(ProductDto product) {
        productService.delete(product.getId());
    }

    public List<Category> getCategories() {
        return categories;
    }

    public List<Brand> getBrands(){return brands;}
}
