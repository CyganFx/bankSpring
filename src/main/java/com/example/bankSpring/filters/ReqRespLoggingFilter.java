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
@Order(2)
public class ReqRespLoggingFilter implements Filter {
    private final static Logger LOG = LoggerFactory.getLogger(com.example.bankSpring.filters.ReqRespLoggingFilter.class);
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;
        LOG.info("Logging Request  {} : {}", req.getMethod(), req.getRequestURI());
        filterChain.doFilter(servletRequest, servletResponse);
        LOG.info("Logging Response :{}", res.getContentType());
    }

    @Override
    public void init(final FilterConfig filterConfig) throws ServletException {
        LOG.info("Initializing filter :{}", this);
    }

    @Override
    public void destroy() {
        LOG.warn("Destructing filter :{}", this);
    }
}
