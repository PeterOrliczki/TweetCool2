package com.codecool.web.servlet;

import com.codecool.web.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
 * Profile is a protected resource, because only logged in users can use it.
 */
@WebServlet("/protected/profile")
public class ProfileServlet extends HttpServlet {

    /*
     * Users after successful login are redirected to the /protected/profile resource. Being redirected there
     * will invoke this method.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        req.setAttribute("user", user);
        req.getRequestDispatcher("profile.jsp").forward(req, resp);
    }
}
