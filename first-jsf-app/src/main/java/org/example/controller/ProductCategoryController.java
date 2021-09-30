package org.example.controller;


import org.example.models.ProductCategory;
import org.example.models.ProductCategoryRepository;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@SessionScoped
@Named
public class ProductCategoryController implements Serializable {

    @Inject
    private ProductCategoryRepository categoryRepository;

    private ProductCategory category;


    public ProductCategory getCategory() {
        return category;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
    }


    public List<ProductCategory> findAll() {
        return categoryRepository.findAll();
    }

    public String editCategory(ProductCategory category) {
        this.category = category;
        return "/category_form.xhtml?faces-redirect=true";
    }

    public String addCategory() {
        this.category = new ProductCategory();
        return "/category_form.xhtml?faces-redirect=true";
    }

    public String saveCategory() {
        categoryRepository.save(category);
        return "/category.xhtml?faces-redirect=true";
    }

    public void deleteCategory(ProductCategory category) {
        categoryRepository.delete(category.getId());
    }
}
