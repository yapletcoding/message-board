package com.techelevator.dao;

import com.techelevator.model.Forum;
import com.techelevator.model.Post;
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
public class JdbcForumDao implements ForumDao{

    private JdbcTemplate jdbcTemplate;

    public JdbcForumDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<Forum> findAll() {
        List<Forum> forums = new ArrayList<>();
        String sql = "SELECT * from forums";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while(results.next()) {
            Forum forum = mapRowToForum(results);
            forums.add(forum);
        }
        return forums;
    }

    public List<Forum> getForumByPartialName(String forumName) {
        List<Forum> forums = new ArrayList<>();
        String sql = "SELECT * from forums WHERE forum_title ILIKE ?;" ;

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, '%'+forumName+'%');
        while(results.next()) {
            Forum forum = mapRowToForum(results);
            forums.add(forum);
        }
        return forums;
    }

    public Forum getForumByForumID(int forumID){
        Forum forum = new Forum();
        String sql = "SELECT * from forums WHERE forums.id = ?;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, forumID);
        if(results.next()){
            forum = mapRowToForum(results);
        }
        return forum;
    }

    @Override
    public Forum getForumByPostID(int postID){
        Forum forum = new Forum();
        String sql = "SELECT * FROM forums " +
                "JOIN posts ON posts.forum_id = forums.id " +
                "WHERE posts.id = ?";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, postID);
        if(results.next()){
            forum = mapRowToForum(results);
        }
        return forum;
    }

    public List<Forum> getTopForums() { //@TODO ordering condition subject to change
        List<Forum> forums = new ArrayList<>();
        String sql = "SELECT * FROM forums ORDER BY forum_posts DESC LIMIT 5;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            Forum forum = mapRowToForum(results);
            forums.add(forum);
        }
        return forums;
    }

    @Override
    public int createForum(String forum_title, String forum_description, int forum_members, int forum_posts) {
        String sql = "";
        sql = "INSERT INTO forums (forum_title, forum_description, forum_members, forum_posts) VALUES (?, ?, 1, 0) RETURNING forums.id";
        int forum_id = jdbcTemplate.queryForObject(sql, int.class, forum_title, forum_description);
        return forum_id;
    }

    @Override
    public void createUserForumData(int user_id, int forum_id) {
        String sql = "";
        sql = "INSERT INTO user_forums (user_id, forum_id, favorite, moderator) VALUES (?, ?, true, true);";
        jdbcTemplate.update(sql, user_id, forum_id);
    }

    @Override
    public void favoriteForum(int user_id, int forum_id) {
        String sequel = "SELECT * FROM user_forums WHERE user_id = ? AND forum_id = ?";
        SqlRowSet rowset = jdbcTemplate.queryForRowSet(sequel, user_id, forum_id);
        if(rowset.next()){
            String sql = "";
            sql = "DELETE FROM user_forums WHERE user_id = ? AND forum_id = ?";
            jdbcTemplate.update(sql, user_id, forum_id);
        }
        else {
            String sql = "";
            sql = "INSERT INTO user_forums (user_id, forum_id, favorite, moderator) VALUES (?, ?, true, false)";
            jdbcTemplate.update(sql, user_id, forum_id);
        }
    }

    @Override
    public List<Forum> findFavoriteForums(int user_id) {
        List<Forum> forums = new ArrayList<>();
        String sql = "SELECT * FROM forums " +
                "JOIN user_forums ON user_forums.forum_id = forums.id " +
                "WHERE user_id = ? AND favorite = true";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, user_id);
        while(results.next()) {
            Forum forum = mapRowToForum(results);
            forums.add(forum);
        }
        return forums;
    }

    private Forum mapRowToForum(SqlRowSet rs) {
        Forum forum = new Forum();
        forum.setForum_id(rs.getInt("id"));
        forum.setForum_description(rs.getString("forum_description"));
        forum.setForum_members(rs.getInt("forum_members"));
        forum.setForum_posts(rs.getInt("forum_posts"));
        forum.setForum_title(rs.getString("forum_title"));
        return forum;
    }
}
