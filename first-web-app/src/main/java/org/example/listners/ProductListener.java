package org.example.listners;

import org.example.models.ProductRepository;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.math.BigDecimal;

@WebListener
public class ProductListener implements ServletContextListener {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(ProductListener.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ProductRepository repository = new ProductRepository();

        log.info("Save product in repository - "+repository.
                saveNewProduct("Sausage", "Tasty",new BigDecimal(500)).toString());
        log.info("Save product in repository - "+repository.
                saveNewProduct("Bred", "Fresh",new BigDecimal(100)).toString());
        log.info("Save product in repository - "+repository.
                saveNewProduct("Apple", "Red",new BigDecimal(200)).toString());
        log.info("Save product in repository - "+repository.
                saveNewProduct("Banana", "Ripe",new BigDecimal(150)).toString());
        log.info("Save product in repository - "+repository.
                saveNewProduct("Pineapple", "Very big",new BigDecimal(5000)).toString());
        log.info("Save product in repository - "+repository.
                saveNewProduct("Dates", "Import",new BigDecimal(500)).toString());

        sce.getServletContext().setAttribute("productRepository", repository);

    }
}
