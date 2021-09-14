package org.example.filter;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(urlPatterns = "/*")
public class EncodingFilter implements Filter {
    private FilterConfig config;
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(EncodingFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.config = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletResponse.setContentType("text/html");
        servletResponse.setCharacterEncoding("UTF-8");
        log.info("do filter");
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
