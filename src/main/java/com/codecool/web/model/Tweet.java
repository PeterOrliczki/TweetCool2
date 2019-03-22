package com.codecool.web.model;

import java.util.Date;

public final class Tweet {
    private int id;
    private String user;
    private String post;
    private Date dateOfPosting;

    public Tweet(int id, String user, String post) {
        this.id = id;
        this.user = user;
        this.post = post;
        this.dateOfPosting = new Date(System.currentTimeMillis());
    }

    public Tweet(int id, String user, String post, Date dateOfposting) {
        this.id = id;
        this.user = user;
        this.post = post;
        this.dateOfPosting = dateOfposting;
    }

    public int getId() {
        return id;
    }

    public String getUser() {
        return user;
    }

    public String getPost() {
        return post;
    }

    public Date getDateOfPosting() {
        return dateOfPosting;
    }
}
