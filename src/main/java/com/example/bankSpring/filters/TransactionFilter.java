package com.example.bankSpring.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Order(1)
public class TransactionFilter implements Filter {
    private final static Logger LOG = LoggerFactory.getLogger(com.example.bankSpring.filters.TransactionFilter.class);

    @Override
    public void init(final FilterConfig filterConfig) throws ServletException {
        LOG.info("Initializing filter :{}", this);
    }
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        LOG.info(
                "Starting a transaction for req : {}",
                request.getRequestURI());
        filterChain.doFilter(request, response);
        LOG.info(
                "Committing a transaction for req : {}",
                request.getRequestURI());
    }


    @Override
    public void destroy() {
        LOG.warn("Destructing filter :{}", this);
    }
}
