package org.example.models;
import java.math.BigDecimal;

public class Product {
    private Long id;
    private String title;
    private String description;
    private BigDecimal price;

    public static class Builder {
        private Product newProduct;

        public Builder() {
            newProduct = new Product();
        }

        public Builder setId(Long id) {
            newProduct.id = id;
            return this;
        }

        public Builder setTitle(String title) {
            newProduct.title = title;
            return this;
        }

        public Builder setDescription(String description) {
            newProduct.description = description;
            return this;
        }

        public Builder setPrice(BigDecimal price) {
            newProduct.price = price;
            return this;
        }

        public Product build() {
            return newProduct;
        }

    }
    public Long getId(){
        return this.id;
    }

    @Override
    public String toString() {
        String hrefLink = "http://127.0.0.1:8080/first-web-app-1.0-SNAPSHOT/products/info?product_id="+this.id;
        return "<td> "+this.id+"</td> "+
                "<td> <a href="+hrefLink+">"+this.title+"<a> </td>"+
                "<td> "+this.description+"</td>"+
                "<td> "+this.price+"</td>";

    }
}
