package org.example.servlets;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/no_page")
public class PageNotFoundServlet extends HttpServlet {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(PageNotFoundServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        log.info("Error - "+req.getContextPath());
        resp.getWriter().println("<h1>Page not found</h1>");
        resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
    }
}
