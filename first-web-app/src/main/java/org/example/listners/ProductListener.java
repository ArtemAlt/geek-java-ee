package org.example.listners;

import org.example.models.ProductRepository;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.math.BigDecimal;

@WebListener
public class ProductListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ProductRepository repository = new ProductRepository();

        repository.saveNewProduct("Колбаса", "Вкусная",new BigDecimal(500));
        repository.saveNewProduct("Хлеб", "Свежий",new BigDecimal(100));
        repository.saveNewProduct("Яблоки", "Красные",new BigDecimal(200));
        repository.saveNewProduct("Бананы", "Спелые",new BigDecimal(150));
        repository.saveNewProduct("Ананас", "Большой",new BigDecimal(5000));
        repository.saveNewProduct("Финики", "Импортные",new BigDecimal(500));

        sce.getServletContext().setAttribute("productRepository", repository);

    }
}
