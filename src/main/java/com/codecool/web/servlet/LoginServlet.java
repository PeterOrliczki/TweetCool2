package com.codecool.web.servlet;

import com.codecool.web.model.User;
import com.codecool.web.service.LoginService;
import com.codecool.web.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("login.jsp").forward(req, resp);
    }

    /*
     * This method is called whenever a user tries to login.
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserService userService = (UserService) req.getServletContext().getAttribute("userService");
        LoginService loginService = (LoginService) req.getServletContext().getAttribute("loginService");

        String email = req.getParameter("email");
        String password = req.getParameter("password");

        if (loginService.login(email, password)) {
            /*
             * If the email and password matches a user in the system we store it's associated User object
             * in the session associated with the request. Then the user is redirected to the page users
             * see after a successful login. Redirection is used instead of dispatching, because this way
             * the URL the user sees in the browser will change - reflecting the page visible after redirection.
             */
            User user = userService.getUser(email);
            req.getSession().setAttribute("user", user);
            /*
             * /profile or profile isn't enough, the redirect will be relative to the URL to current request's URL.
             */
            resp.sendRedirect("protected/profile");
        } else {
            /*
             * If the login credentials didn't match a user in the system we want the user to see the login
             * page again with some extra info on what's happened. For this we add an extra attribute to the
             * original request and dispatch it to the login.jsp - this will send back an HTML (containing the
             * reason why login failed). The user isn't redirected, the browser will display the same URL in its
             * address bar.
             */
            req.setAttribute("error", "Incorrect login parameters.");
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        }
    }
}
