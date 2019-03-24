package com.codecool.web.service;

import com.codecool.web.model.Tweet;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TweetListTest {

    private Tweet tweet;
    private TweetList tweetList;

    @BeforeEach
    void makingTweetsIntoTweetList() {
        tweetList = new TweetList();
        tweet = new Tweet(1, "a", "a", new Date(100000000000L));
        tweetList.addTweet(tweet);
    }

    @Test
    void addTweetTest() {
        tweetList.addTweet(tweet);
        assertTrue(tweetList.getTweets().contains(tweet));
    }

    @Test
    void getTweetTest() {
        assertNotNull(tweetList.getTweets());
    }

    @AfterEach
    void nullifying() {
        tweetList = null;
        tweet = null;
    }

}
