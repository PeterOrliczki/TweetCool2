package com.codecool.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
 * Logout is a protected resource, because only logged in users can use it.
 */
@WebServlet("/protected/logout")
public class LogoutServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().invalidate();
        /*
         * The redirect is sent to .., mening "up one directory", because it'll be relative to the current request's
         * URL which is /protected/logout. "Up one folder" means the browser will be redirected to /, because the
         * logout resource is in the /protected directory.
         */
        resp.sendRedirect("..");
    }
}
