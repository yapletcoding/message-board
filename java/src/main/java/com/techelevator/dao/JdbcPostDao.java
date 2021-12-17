package com.techelevator.dao;

import com.techelevator.model.Forum;
import com.techelevator.model.Post;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.security.Timestamp;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Component
public class JdbcPostDao implements PostDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcPostDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Post> getPostsByForumId(int forumId) {
        List<Post> posts = new ArrayList<>();
        String sql = "SELECT * FROM posts WHERE forum_id = ? ORDER BY post_datetime DESC, (post_likes - post_dislikes) DESC;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, forumId);
        while(results.next()) {
            Post post = mapRowToPost(results);
            posts.add(post);
        }
        return posts;
    }

    public List<Post> getTopPosts() {
        List<Post> posts = new ArrayList<>();
        String sql = "SELECT * FROM posts ORDER BY post_datetime DESC, (post_likes - post_dislikes) DESC LIMIT 10;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while(results.next()) {
            Post post = mapRowToPost(results);
            posts.add(post);
        }
        return posts;
    }

    public List<Post> getPopularPosts(int forumId) {
        List<Post> posts = new ArrayList<>();
        String sql = "SELECT * FROM posts WHERE forum_id = ? ORDER BY (post_likes - post_dislikes) DESC;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, forumId);
        while(results.next()) {
            Post post = mapRowToPost(results);
            posts.add(post);
        }
        return posts;
    }

    public List<Post> getRecentPosts(int forumId) {
        List<Post> posts = new ArrayList<>();
        String sql = "SELECT * FROM posts WHERE forum_id = ? ORDER BY post_datetime DESC;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, forumId);
        while(results.next()) {
            Post post = mapRowToPost(results);
            posts.add(post);
        }
        return posts;
    }

    public Post getPostByPostID(int postID){
        Post post = new Post();
        String sql = "SELECT * FROM posts WHERE posts.id = ?;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, postID);
        if(results.next()){
            post = mapRowToPost(results);
        }
        return post;
    }

    @Override
    public void createPost(int forum_id, int user_id, String post_title, String post_text, int post_likes, int post_dislikes, int post_comments, LocalDate post_datetime, String post_img) {
        String sql = "";
            sql = "INSERT INTO posts (forum_id, user_id, post_title, post_text, post_likes, post_dislikes, post_comments, post_datetime, post_img) VALUES (?, ?, ?, ?, 0, 0, 0, ?, ?);";
            jdbcTemplate.update(sql, forum_id, user_id, post_title, post_text, post_datetime, post_img);
        }

    @Override
    public void deletePost(int post_id) {
        String sequel = "";
        sequel = "DELETE FROM user_comments WHERE post_id = ?";
        jdbcTemplate.update(sequel, post_id);
        String sequel1 = "";
        sequel1 = "DELETE FROM user_posts WHERE post_id = ?";
        jdbcTemplate.update(sequel1, post_id);
        String sql = "";
        sql = "DELETE FROM comments WHERE post_id = ?";
        jdbcTemplate.update(sql, post_id);
        String sql2 = "";
        sql2 = "DELETE FROM posts WHERE posts.id = ?";
        jdbcTemplate.update(sql2, post_id);
    }

    @Override
    public List<Post> findAllPosts() {
        List<Post> posts = new ArrayList<>();
        String sql = "SELECT * from posts";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while(results.next()) {
            Post post = mapRowToPost(results);
            posts.add(post);
        }
        return posts;
    }

    @Override
    public List<Post> getPostByPartialName(String postName) {
        List<Post> posts = new ArrayList<>();
        String sql = "SELECT * from posts WHERE post_title ILIKE ?;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, '%'+postName+'%');
        while(results.next()) {
            Post post = mapRowToPost(results);
            posts.add(post);
        }
        return posts;
    }

    @Override
    public void updatePostLikes(int post_id, int user_id) {
        String sequel = "SELECT * FROM user_posts WHERE user_id = ? AND post_id = ?";
        SqlRowSet rowset = jdbcTemplate.queryForRowSet(sequel, user_id, post_id);
        if(rowset.next()) {
            boolean post_interaction_like = rowset.getBoolean("post_interaction_like");
            boolean post_interaction_dislike = rowset.getBoolean("post_interaction_dislike");
            if (!post_interaction_like && !post_interaction_dislike) {
                String sql = "";
                sql = "UPDATE posts ";
                sql += "SET post_likes = post_likes + 1 ";
                sql += "WHERE posts.id = ?;";
                jdbcTemplate.update(sql, post_id);
                String sql2 = "";
                sql2 = "UPDATE user_posts SET post_interaction_like = true WHERE user_id = ? AND post_id = ?";
                jdbcTemplate.update(sql2, user_id, post_id);
            } else if (!post_interaction_like && post_interaction_dislike) {
                String sql2 = "";
                sql2 = "UPDATE user_posts SET post_interaction_like = false WHERE user_id = ? AND post_id = ?";
                jdbcTemplate.update(sql2, user_id, post_id);
            } else {
                String sql = "";
                sql = "UPDATE posts ";
                sql += "SET post_likes = post_likes - 1 ";
                sql += "WHERE posts.id = ?;";
                jdbcTemplate.update(sql, post_id);
                String sql2 = "";
                sql2 = "UPDATE user_posts SET post_interaction_like = false WHERE user_id = ? AND post_id = ?";
                jdbcTemplate.update(sql2, user_id, post_id);
            }
        }
        else {
            String sql = "";
            sql = "UPDATE posts ";
            sql += "SET post_likes = post_likes + 1 ";
            sql += "WHERE posts.id = ?;";
            jdbcTemplate.update(sql, post_id);
            String sql2 = "";
            sql2 = "INSERT INTO user_posts (user_id, post_id, post_interaction_like, post_interaction_dislike) VALUES (?, ?, true, false)";
            jdbcTemplate.update(sql2, user_id, post_id);
        }
    }

    @Override
    public void updatePostDislikes(int post_id, int user_id) {
        String sequel = "SELECT * FROM user_posts WHERE user_id = ? AND post_id = ?";
        SqlRowSet rowset = jdbcTemplate.queryForRowSet(sequel, user_id, post_id);
        if(rowset.next()) {
            boolean post_interaction_like = rowset.getBoolean("post_interaction_like");
            boolean post_interaction_dislike = rowset.getBoolean("post_interaction_dislike");
            if (!post_interaction_like && !post_interaction_dislike) {
                String sql = "";
                sql = "UPDATE posts ";
                sql += "SET post_dislikes = post_dislikes + 1 ";
                sql += "WHERE posts.id = ?;";
                jdbcTemplate.update(sql, post_id);
                String sql2 = "";
                sql2 = "UPDATE user_posts SET post_interaction_dislike = true WHERE user_id = ? AND post_id = ?";
                jdbcTemplate.update(sql2, user_id, post_id);
            }
            else if(post_interaction_like && !post_interaction_dislike){
                String sql2 = "";
                sql2 = "UPDATE user_posts SET post_interaction_dislike = false WHERE user_id = ? AND post_id = ?";
                jdbcTemplate.update(sql2, user_id, post_id);
            }
            else{
                String sql = "";
                sql = "UPDATE posts ";
                sql += "SET post_dislikes = post_dislikes - 1 ";
                sql += "WHERE posts.id = ?;";
                jdbcTemplate.update(sql, post_id);
                String sql2 = "";
                sql2 = "UPDATE user_posts SET post_interaction_dislike = false WHERE user_id = ? AND post_id = ?";
                jdbcTemplate.update(sql2, user_id, post_id);
            }
        }
        else {
            String sql = "";
            sql = "UPDATE posts ";
            sql += "SET post_dislikes = post_dislikes + 1 ";
            sql += "WHERE posts.id = ?;";
            jdbcTemplate.update(sql, post_id);
            String sql2 = "";
            sql2 = "INSERT INTO user_posts (user_id, post_id, post_interaction_like, post_interaction_dislike) VALUES (?, ?, false, true)";
            jdbcTemplate.update(sql2, user_id, post_id);
        }
    }

    @Override
    public List<Post> findMyPosts(int user_id) {
        List<Post> posts = new ArrayList<>();
        String sql = "SELECT * FROM posts WHERE user_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, user_id);
        while(results.next()) {
            Post post = mapRowToPost(results);
            posts.add(post);
        }
        return posts;
    }

    private Post mapRowToPost(SqlRowSet rs) {
        Post post = new Post();
        post.setPost_id(rs.getInt("id"));
        post.setForum_id(rs.getInt("forum_id"));
        post.setUser_id(rs.getInt("user_id"));
        post.setPost_title(rs.getString("post_title"));
        post.setPost_text(rs.getString("post_text"));
        post.setPost_likes(rs.getInt("post_likes"));
        post.setPost_dislikes(rs.getInt("post_dislikes"));
        post.setPost_comments(rs.getInt("post_comments"));
        post.setPost_datetime(rs.getTimestamp("post_datetime"));
        post.setPost_img(rs.getString("post_img"));
        return post;
    }


}
