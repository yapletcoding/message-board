package com.techelevator.dao;

import com.techelevator.model.Forum;
import com.techelevator.model.Post;

import java.util.List;

public interface ForumDao {

    public List<Forum> findAll();

    public List<Forum> getForumByPartialName(String forumName);

    public Forum getForumByForumID(int forumID);

    public List<Forum> getTopForums();

    public int createForum(String forum_title, String forum_description, int forum_members, int forum_posts);

    public void createUserForumData(int user_id, int forum_id);

    public void favoriteForum(int user_id, int forum_id);

    public List<Forum> findFavoriteForums(int user_id);

    public Forum getForumByPostID(int postID);


}
