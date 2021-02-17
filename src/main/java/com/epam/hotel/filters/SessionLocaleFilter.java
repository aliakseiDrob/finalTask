package com.epam.hotel.filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SessionLocaleFilter implements Filter {
    private static final String LOCALE = "locale";


    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        String language = req.getParameter(LOCALE);
        if (language != null) {
            HttpSession session = req.getSession();
            session.setAttribute(LOCALE, language);
        }
        chain.doFilter(request, response);

    }

    @Override
    public void destroy() {

    }
}

