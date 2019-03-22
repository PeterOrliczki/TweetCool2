package com.codecool.web.service;

import com.codecool.web.model.Tweet;

import java.util.ArrayList;
import java.util.List;


public class TweetList {

    private List<Tweet> tweets;
    private static TweetList singletonInstance = new TweetList();

    public static TweetList getInstance() {
        return singletonInstance;
    }

    public TweetList() {
        tweets = new ArrayList<>();
    }

    public void addTweet(Tweet tweet) {
        tweets.add(tweet);
    }

    public List<Tweet> getTweets() {
        return tweets;
    }
}
