package com.Filter;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.*;
import java.io.IOException;


public class LoginFilter2 implements Filter {
    private Log log = LogFactory.getLog(LoginFilter2.class);

    public void init(FilterConfig filterConfig) throws ServletException {
        log.error("initinitinitin22222LoginFilter2");
         }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {


        log.error("LoginFilter2doFilter222222222222");
        chain.doFilter(request,response);

    }

    public void destroy() {
        log.error("LoginFilter2");
    }

}
