package org.example.models;

public class ProductCategory {
    private Long id;

    private String title;

    public ProductCategory(Long id, String title) {
        this.id = id;
        this.title = title;
    }

    public ProductCategory() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
