package com.codecool.web.service;

import com.codecool.web.model.Tweet;
import com.codecool.web.model.TweetAttribute;

import java.util.Date;

public class TweetListAttribution {

    private int limit;
    private int offset;
    private TweetAttribute tweetAttribute;

    public TweetListAttribution(int limit, int offset) {
        this.limit = limit;
        this.offset = offset;
    }

    public TweetList searchForPost(TweetList tweetList) {  // searches post by limit and offset
        TweetList searched = new TweetList();
        if (tweetList.getTweets().size() < limit) {
            limit = tweetList.getTweets().size();
        }
        for (int i = offset; i < limit; i++) {
            searched.addTweet(tweetList.getTweets().get(i));
        }
        return searched;
    }

    public TweetList searchForPost(TweetList tweetList, String poster) {  // searches post by poster
        TweetList searched = new TweetList();
        for (Tweet i : tweetList.getTweets()
        ) {
            if (i.getUser().equals(poster)) {
                searched.addTweet(i);
            }
        }
        return searched;
    }

    public TweetList searchForPost(TweetList tweetList, Date date) {  // searches post by date of posting
        TweetList searched = new TweetList();
        for (Tweet i : tweetList.getTweets()
        ) {
            if (i.getDateOfPosting().compareTo(date) >= 0) {
                searched.addTweet(i);
            }
        }
        return searched;
    }

    public TweetList searchForPost(TweetList tweetList, String poster, Date date) {  // searches post by both
        TweetList searched = searchForPost(tweetList, poster);
        searched = searchForPost(searched, date);
        return searched;
    }

    public TweetList givingBackSearchedPost(TweetList tweetList, String user, Date date) {
        TweetList searched = new TweetList();
        if (user != null && date != null) {
            tweetAttribute = TweetAttribute.POSTERANDDATE;
        } else if (user != null) {
            tweetAttribute = TweetAttribute.POSTER;
        } else if (date != null) {
            tweetAttribute = TweetAttribute.DATE;
        } else {
            tweetAttribute = TweetAttribute.NOATTRIBUTE;
        }
        if (tweetAttribute.equals(TweetAttribute.POSTER)) {  // this was the point i realized overloaded methods were a mistake (were they though?)
            searched = searchForPost(tweetList, user);
        } else if (tweetAttribute.equals(TweetAttribute.DATE)) {
            searched = searchForPost(tweetList, date);
        } else if (tweetAttribute.equals(TweetAttribute.POSTERANDDATE)) {
            searched = searchForPost(tweetList, user, date);
        }
        return searchForPost(searched);
    }
}
