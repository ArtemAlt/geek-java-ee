package org.example;

import org.example.models.Product;
import org.example.models.ProductRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet(urlPatterns = "/products")
public class ProductsHTTPServlet extends HttpServlet {
    private ProductRepository repository;
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(ProductsHTTPServlet.class);


    @Override
    public void init() throws ServletException {
       this.repository= (ProductRepository) getServletContext().getAttribute("productRepository");
       log.info("init product HTTP servlet");
       log.info("product repository load - " + getServletContext().getAttribute("productRepository").getClass());
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("incoming request--------------------- \n" +
                "contextPath: "+ req.getContextPath() +"\n"+
                "servletPath: "+ req.getServletPath() +"\n"+
                "pathInfo: "+ req.getPathInfo()+"\n"+
                "queryString: "+ req.getQueryString()+"\n"+
                "---------------------------------------");
        resp.getWriter().println("<table>");
        resp.getWriter().println("<tr> <td>Product ID</td> " +
                "<td>Product title</td> " +
                "<td>Product description</td> " +
                "<td>Product price</td>" );

        for (Product p : repository.getAll()){
            resp.getWriter().println("<tr>");
            resp.getWriter().println(p.toString());
            resp.getWriter().println("</tr>");
        }
        resp.getWriter().println("</table>");
    }


}
