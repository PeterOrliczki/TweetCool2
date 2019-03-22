package com.codecool.web.servlet;

import com.codecool.web.service.TweetList;
import com.codecool.web.service.TweetListAttribution;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/tweets")
public class TweetListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        TweetList tweets = (TweetList) request.getSession().getAttribute("tweets");
        request.setAttribute("tweets", tweets);
        request.getRequestDispatcher("tweets.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        int limit = Integer.parseInt(request.getParameter("limit"));
        int offset = Integer.parseInt(request.getParameter("offset"));
        String poster = request.getParameter("poster");
        String dateString = request.getParameter("date");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm");
        TweetList tweets = (TweetList) request.getSession().getAttribute("tweets");
        Date date = null;
        try {
            date = df.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        TweetList searched = null;
        if (tweets != null) {
            TweetListAttribution filter = new TweetListAttribution(limit, offset);
            searched = filter.givingBackSearchedPost(tweets, poster, date);
        }
        request.setAttribute("tweets", searched);
        request.getRequestDispatcher("tweets.jsp").include(request, response);
    }

}
