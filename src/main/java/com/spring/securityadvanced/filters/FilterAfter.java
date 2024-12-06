package com.spring.securityadvanced.filters;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import org.springframework.core.annotation.Order;
public class FilterAfter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("Inside Filter After");
        filterChain.doFilter(servletRequest,servletResponse);
    }
}
