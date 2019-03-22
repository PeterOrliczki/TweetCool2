package com.codecool.web.filter;

import com.codecool.web.model.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/*
 * The filter is mapped to the /protected/* wildcard URL pattern.
 * The reason is that this we can separate "resoures": unprotected and protected.
 * In this webapp when a browser requests an unprotected resource it'll send requests to URL patterns
 * like /login, /index.jsp, /faq.html, /style.css, etc.
 * When a protected resource is requested it'll have an URL pattern like /protected/profile, /protected/logout, etc.
 */
@WebFilter("/protected/*")
public final class SessionFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            resp.sendRedirect("../login.jsp");
        } else {
            chain.doFilter(req, resp);
        }
    }

    @Override
    public void destroy() {
    }
}
