package com.techelevator.dao;

import com.techelevator.model.Post;

import java.security.Timestamp;
import java.time.LocalDate;
import java.util.List;

public interface PostDao {
    public List<Post> getPostsByForumId(int forumId);

    public void createPost(int forum_id, int user_id, String post_title, String post_text, int post_likes, int post_dislikes, int post_comments, LocalDate post_datetime, String post_img);

    public List<Post> getTopPosts();

    public List<Post> getPopularPosts(int forumId);

    public List<Post> getRecentPosts(int forumId);

    public Post getPostByPostID(int postID);

    public void deletePost(int post_id);

    public List<Post> getPostByPartialName(String postName);

    public List<Post> findAllPosts();

    public void updatePostLikes(int post_id, int user_id);

    public void updatePostDislikes(int post_id, int user_id);

    public List<Post> findMyPosts(int user_id);


}
