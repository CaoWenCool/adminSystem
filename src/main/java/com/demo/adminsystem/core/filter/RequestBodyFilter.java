package com.demo.adminsystem.core.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author: admin
 * @create: 2019/3/20
 * @update: 18:26
 * @version: V1.0
 * @detail:
 **/
@Configuration
@WebFilter(filterName = "sessionFilter", urlPatterns = {"/*"})
public class RequestBodyFilter implements Filter {
    private final static Logger LOGGER = LoggerFactory.getLogger(RequestBodyFilter.class);
    private final static String PUT = "PUT";
    private final static String POST = "POST";


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        BodyReaderHttpServletRequestWrapper requestWrapper = null;
        if (request instanceof HttpServletRequest) {
            HttpServletRequest httpServletRequest = (HttpServletRequest) request;
            String method = httpServletRequest.getMethod().toUpperCase();
            if (POST.equals(method) || PUT.equals(method)) {
                requestWrapper = new BodyReaderHttpServletRequestWrapper(
                        httpServletRequest);
            }
        }
        if (requestWrapper == null) {
            filterChain.doFilter(request, response);
        } else {
            filterChain.doFilter(requestWrapper, response);
        }

    }

    @Override
    public void destroy() {

    }
}
