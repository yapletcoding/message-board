package com.techelevator.model;

import java.sql.Timestamp;
import java.time.LocalDate;

public class Post {
    private int post_id;
    private int forum_id;
    private int user_id;
    private String post_title;
    private String post_text;
    private int post_likes;
    private int post_dislikes;
    private int post_comments;
    private Timestamp post_datetime;
    private String post_img;

    public int getPost_id() {
        return post_id;
    }

    public void setPost_id(int post_id) {
        this.post_id = post_id;
    }

    public int getForum_id() {
        return forum_id;
    }

    public void setForum_id(int forum_id) {
        this.forum_id = forum_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getPost_title() {
        return post_title;
    }

    public void setPost_title(String post_title) {
        this.post_title = post_title;
    }

    public String getPost_text() {
        return post_text;
    }

    public void setPost_text(String post_text) {
        this.post_text = post_text;
    }

    public int getPost_likes() {
        return post_likes;
    }

    public void setPost_likes(int post_likes) {
        this.post_likes = post_likes;
    }

    public int getPost_dislikes() {
        return post_dislikes;
    }

    public void setPost_dislikes(int post_dislikes) {
        this.post_dislikes = post_dislikes;
    }

    public int getPost_comments() {
        return post_comments;
    }

    public void setPost_comments(int post_comments) {
        this.post_comments = post_comments;
    }

    public Timestamp getPost_datetime() {
        return post_datetime;
    }

    public void setPost_datetime(Timestamp post_datetime) {
        this.post_datetime = post_datetime;
    }

    public String getPost_img() {
        return post_img;
    }

    public void setPost_img(String post_img) {
        this.post_img = post_img;
    }
}
