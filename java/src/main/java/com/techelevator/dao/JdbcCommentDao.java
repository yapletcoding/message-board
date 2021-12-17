package com.techelevator.dao;

import com.techelevator.model.Comment;
import com.techelevator.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Component
public class JdbcCommentDao implements CommentDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcCommentDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<Comment> getCommentsByPostID(int postID) {
        List<Comment> comments = new ArrayList<>();
        String sql = "SELECT * FROM comments WHERE post_id = ?";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, postID);
        while (results.next()) {
            Comment comment = mapRowToComment(results);
            comments.add(comment);
        }
        return comments;
    }

    @Override
    public void createComment(int post_id, Integer replying_to_id, int user_id, int comment_likes, int comment_dislikes, LocalDate comment_datetime, String comment_text) {
        String sql = "";
        sql = "INSERT INTO comments (post_id, replying_to_id, user_id, comment_likes, comment_dislikes, comment_datetime, comment_text) VALUES (?, null, ?, 0, 0, ?, ?);";
        jdbcTemplate.update(sql, post_id, user_id, comment_datetime, comment_text);
    }

    @Override
    public void updateCommentLikes(int comment_id, int user_id, int post_id) {
        String sequel = "SELECT * FROM user_comments WHERE user_id = ? AND comment_id = ?";
        SqlRowSet rowset = jdbcTemplate.queryForRowSet(sequel, user_id, comment_id);
        if(rowset.next()) {
            boolean comment_interaction_like = rowset.getBoolean("comment_interaction_like");
            boolean comment_interaction_dislike = rowset.getBoolean("comment_interaction_dislike");
            if (!comment_interaction_like && !comment_interaction_dislike) {
                String sql = "";
                sql = "UPDATE comments ";
                sql += "SET comment_likes = comment_likes + 1 ";
                sql += "WHERE comments.id = ?;";
                jdbcTemplate.update(sql, comment_id);
                String sql2 = "";
                sql2 = "UPDATE user_comments SET comment_interaction_like = true WHERE user_id = ? AND comment_id = ?";
                jdbcTemplate.update(sql2, user_id, comment_id);
            } else if (!comment_interaction_like && comment_interaction_dislike) {
                String sql2 = "";
                sql2 = "UPDATE user_comments SET comment_interaction_like = false WHERE user_id = ? AND comment_id = ?";
                jdbcTemplate.update(sql2, user_id, comment_id);
            } else {
                String sql = "";
                sql = "UPDATE comments ";
                sql += "SET comment_likes = comment_likes - 1 ";
                sql += "WHERE comments.id = ?;";
                jdbcTemplate.update(sql, comment_id);
                String sql2 = "";
                sql2 = "UPDATE user_comments SET comment_interaction_like = false WHERE user_id = ? AND comment_id = ?";
                jdbcTemplate.update(sql2, user_id, comment_id);
            }
        }
            else {
                String sql = "";
                sql = "UPDATE comments ";
                sql += "SET comment_likes = comment_likes + 1 ";
                sql += "WHERE comments.id = ?;";
                jdbcTemplate.update(sql, comment_id);
                String sql2 = "";
                sql2 = "INSERT INTO user_comments (user_id, post_id, comment_id, comment_interaction_like, comment_interaction_dislike) VALUES (?, ?, ?, true, false)";
                jdbcTemplate.update(sql2, user_id, post_id, comment_id);
            }
        }

    @Override
    public void updateCommentDislikes(int comment_id, int user_id, int post_id) {
        String sequel = "SELECT * FROM user_comments WHERE user_id = ? AND comment_id = ?";
        SqlRowSet rowset = jdbcTemplate.queryForRowSet(sequel, user_id, comment_id);
        if(rowset.next()) {
            boolean comment_interaction_like = rowset.getBoolean("comment_interaction_like");
            boolean comment_interaction_dislike = rowset.getBoolean("comment_interaction_dislike");
            if (!comment_interaction_like && !comment_interaction_dislike) {
                String sql = "";
                sql = "UPDATE comments ";
                sql += "SET comment_dislikes = comment_dislikes + 1 ";
                sql += "WHERE comments.id = ?;";
                jdbcTemplate.update(sql, comment_id);
                String sql2 = "";
                sql2 = "UPDATE user_comments SET comment_interaction_dislike = true WHERE user_id = ? AND comment_id = ?";
                jdbcTemplate.update(sql2, user_id, comment_id);
            }
            else if(comment_interaction_like && !comment_interaction_dislike){
                String sql2 = "";
                sql2 = "UPDATE user_comments SET comment_interaction_dislike = false WHERE user_id = ? AND comment_id = ?";
                jdbcTemplate.update(sql2, user_id, comment_id);
            }
            else{
                String sql = "";
                sql = "UPDATE comments ";
                sql += "SET comment_dislikes = comment_dislikes - 1 ";
                sql += "WHERE comments.id = ?;";
                jdbcTemplate.update(sql, comment_id);
                String sql2 = "";
                sql2 = "UPDATE user_comments SET comment_interaction_dislike = false WHERE user_id = ? AND comment_id = ?";
                jdbcTemplate.update(sql2, user_id, comment_id);
            }
        }
        else {
                String sql = "";
                sql = "UPDATE comments ";
                sql += "SET comment_dislikes = comment_dislikes + 1 ";
                sql += "WHERE comments.id = ?;";
                jdbcTemplate.update(sql, comment_id);
                String sql2 = "";
                sql2 = "INSERT INTO user_comments (user_id, post_id, comment_id, comment_interaction_like, comment_interaction_dislike) VALUES (?, ?, ?, false, true)";
                jdbcTemplate.update(sql2, user_id, post_id, comment_id);
            }
        }




    private Comment mapRowToComment(SqlRowSet rs) {
        Comment comment = new Comment();
        comment.setId(rs.getInt("id"));
        comment.setPost_id(rs.getInt("post_id"));
        comment.setReplying_to_id(rs.getInt("replying_to_id"));
        comment.setUser_id(rs.getInt("user_id"));
        comment.setComment_likes(rs.getInt("comment_likes"));
        comment.setComment_dislikes(rs.getInt("comment_dislikes"));
        comment.setComment_text(rs.getString("comment_text"));
        return comment;
    }

}



