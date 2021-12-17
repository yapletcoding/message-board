package com.techelevator.dao;

import com.techelevator.model.Comment;
import com.techelevator.model.User;

import java.time.LocalDate;
import java.util.List;

public interface CommentDao {

    public List<Comment> getCommentsByPostID(int postID);

    public void createComment(int post_id, Integer replying_to_id, int user_id, int comment_likes, int comment_dislikes, LocalDate comment_datetime, String comment_text);

    public void updateCommentLikes(int comment_id, int user_id, int post_id);

    public void updateCommentDislikes(int comment_id, int user_id, int post_id);

}
