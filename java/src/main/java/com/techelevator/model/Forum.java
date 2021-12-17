package com.techelevator.model;

public class Forum {
    private int forum_id;
    private String forum_title;
    private String forum_description;
    private int forum_members;
    private int forum_posts;

    public int getForum_id() {
        return forum_id;
    }

    public void setForum_id(int forum_id) {
        this.forum_id = forum_id;
    }

    public String getForum_title() {
        return forum_title;
    }

    public void setForum_title(String forum_title) {
        this.forum_title = forum_title;
    }

    public String getForum_description() {
        return forum_description;
    }

    public void setForum_description(String forum_description) {
        this.forum_description = forum_description;
    }

    public int getForum_members() {
        return forum_members;
    }

    public void setForum_members(int forum_members) {
        this.forum_members = forum_members;
    }

    public int getForum_posts() {
        return forum_posts;
    }

    public void setForum_posts(int forum_posts) {
        this.forum_posts = forum_posts;
    }
}
