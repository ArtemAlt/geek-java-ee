package org.example;

import javax.servlet.*;
import java.io.IOException;

public class FirstServlet implements Servlet {
    private ServletConfig config;

    @Override
    public void init(ServletConfig servletConfig) {
        this.config = servletConfig;
    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws  IOException {
        servletResponse.getWriter().println("<h1>Hello from servlet!</h1>");
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }
}
