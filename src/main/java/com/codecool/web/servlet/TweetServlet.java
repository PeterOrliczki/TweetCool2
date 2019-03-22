package com.codecool.web.servlet;

import com.codecool.web.listener.WebappContextListener;
import com.codecool.web.model.Tweet;
import com.codecool.web.service.TweetList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/new-tweet")
public class TweetServlet extends HttpServlet {

    private int i = 0;
    private TweetList tweets = new TweetList();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String user = request.getParameter("user");
        String post = request.getParameter("post");
        Tweet tweet = new Tweet(i++, user, post);
        if (!WebappContextListener.xmlReaderAndWriter.getTweetList().getTweets().isEmpty()) {
            tweets = WebappContextListener.xmlReaderAndWriter.getTweetList();
        } else {
            tweets.addTweet(tweet);
        }
        WebappContextListener.xmlReaderAndWriter.getTweetList().addTweet(tweet);
        request.getSession().setAttribute("tweets", tweets);
        request.setAttribute("user", user);
        request.setAttribute("post", post);
        request.getRequestDispatcher("index.html").forward(request, response);
    }
}
