package org.example.servlets;

import org.example.exc.NotFoundException;
import org.example.models.Product;
import org.example.models.ProductRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet("/products/*")
public class ProductControllerServlet extends HttpServlet {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(ProductControllerServlet.class);
    private static final Pattern pattern = Pattern.compile("\\/(\\d*)$");
    private ProductRepository repository;

    @Override
    public void init() {
        this.repository = (ProductRepository) getServletContext().getAttribute("productRepository");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getPathInfo() == null || req.getPathInfo().equals("/")) {
            req.setAttribute("productList", repository.getAll());
            getServletContext().getRequestDispatcher("/WEB-INF/views/products.jsp").forward(req, resp);
        }
        if (req.getPathInfo().equals("/new")) {
            req.setAttribute("product", new Product());
            getServletContext().getRequestDispatcher("/WEB-INF/views/product_form.jsp").forward(req, resp);
        } else {
            Matcher matcher = pattern.matcher(req.getPathInfo());
            if (matcher.matches()) {
                long id;
                try {
                    id = Long.parseLong(matcher.group(1));
                } catch (NumberFormatException ex) {
                    resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    return;
                }
                req.setAttribute("product", repository.getProductById(id)
                        .orElseThrow(NotFoundException::new));
                getServletContext().getRequestDispatcher("/WEB-INF/views/product_form.jsp").forward(req, resp);
                return;
            }
        }
        resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        log.info("Post request - " + req.getPathInfo());
        if (req.getPathInfo() == null || req.getPathInfo().equals("/")) {
            String strId = req.getParameter("id");
            Long id = null;
            try {
                if (strId != null && !strId.isEmpty()) {
                    id = Long.parseLong(strId);
                }
                Product product = new Product(id,
                        req.getParameter("name"),
                        req.getParameter("description"),
                        new BigDecimal(req.getParameter("price")));
                repository.save(product);
                resp.sendRedirect(getServletContext().getContextPath() + "/products");
            } catch (NumberFormatException ex) {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }

        }
    }
}
