package ch.heg.examen.todolistapp.services;


import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Map;


@WebFilter(urlPatterns="/*")
public class ServiceFilter implements Filter{

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println(" =============  Init LogFilter");
        filterConfig.getInitParameter("myParams");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println(" =============  Before LogFilter");


        HttpServletRequest req = (HttpServletRequest)servletRequest;
        long startTime = System.currentTimeMillis();
        filterChain.doFilter(servletRequest, servletResponse);
        long endTime = System.currentTimeMillis();


        System.out.println(req.getMethod() + " " + req.getRequestURL() + " en " + (endTime - startTime) + " ms");

        System.out.println(" =============  After LogFilter");
    }

    @Override public void destroy() {
        System.out.println(" =============  Destroy LogFilter");
    }





}
