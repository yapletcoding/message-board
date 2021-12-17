package com.techelevator.model;

import java.security.Timestamp;

public class Comment {
    private int id;
    private int post_id;
    private int replying_to_id;
    private int user_id;
    private int comment_likes;
    private int comment_dislikes;
    private Timestamp comment_datetime;
    private String comment_text;
    private boolean comment_interaction_like;
    private boolean comment_interaction_dislike;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPost_id() {
        return post_id;
    }

    public void setPost_id(int post_id) {
        this.post_id = post_id;
    }

    public int getReplying_to_id() {
        return replying_to_id;
    }

    public void setReplying_to_id(int replying_to_id) {
        this.replying_to_id = replying_to_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getComment_likes() {
        return comment_likes;
    }

    public void setComment_likes(int comment_likes) {
        this.comment_likes = comment_likes;
    }

    public int getComment_dislikes() {
        return comment_dislikes;
    }

    public void setComment_dislikes(int comment_dislikes) {
        this.comment_dislikes = comment_dislikes;
    }

    public Timestamp getComment_datetime() {
        return comment_datetime;
    }

    public void setComment_datetime(Timestamp comment_datetime) {
        this.comment_datetime = comment_datetime;
    }

    public String getComment_text() {
        return comment_text;
    }

    public void setComment_text(String comment_text) {
        this.comment_text = comment_text;
    }

    public boolean isComment_interaction_like() {
        return comment_interaction_like;
    }

    public void setComment_interaction_like(boolean comment_interaction_like) {
        this.comment_interaction_like = comment_interaction_like;
    }

    public boolean isComment_interaction_dislike() {
        return comment_interaction_dislike;
    }

    public void setComment_interaction_dislike(boolean comment_interaction_dislike) {
        this.comment_interaction_dislike = comment_interaction_dislike;
    }
}
