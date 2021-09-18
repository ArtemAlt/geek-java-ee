package org.example;


import org.example.models.Product;
import org.example.models.ProductRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet(urlPatterns = "/products/info")
public class ProductInfoHTTPServlet extends HttpServlet {
    private ProductRepository repository;
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(ProductInfoHTTPServlet.class);
    private final String back="http://127.0.0.1:8080/first-web-app-1.0-SNAPSHOT/products";


    @Override
    public void init() throws ServletException {
        this.repository = (ProductRepository) getServletContext().getAttribute("productRepository");
        log.info("init product HTTP servlet");
        log.info("product repository load - " + getServletContext().getAttribute("productRepository").getClass());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("incoming request--------------------- \n" +
                "contextPath: " + req.getContextPath() + "\n" +
                "servletPath: " + req.getServletPath() + "\n" +
                "pathInfo: " + req.getPathInfo() + "\n" +
                "queryString: " + req.getQueryString() + "\n" +
                "---------------------------------------");

        Long id = Long.parseLong(req.getParameter("product_id"));
        Optional<Product> result = repository.getProductById(id);
        if (result.isPresent()) {
            resp.getWriter().println("<tr>");
            resp.getWriter().println(result.get().toString());
            resp.getWriter().println("</tr>");
        } else {
            resp.getWriter().println("<tr>");
            resp.getWriter().println("No such id product");
            resp.getWriter().println("</tr>");
        }
        resp.getWriter().println("<tr><a href ="+back+"> Back to product list </a></tr>");
    }

}



