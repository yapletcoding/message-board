package com.techelevator.controller;

import com.techelevator.dao.ForumDao;
import com.techelevator.dao.JdbcForumDao;
import com.techelevator.dao.JdbcUserDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.Forum;
import com.techelevator.model.Message;
import com.techelevator.model.Post;
import com.techelevator.model.User;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@PreAuthorize("isAuthenticated()")
@CrossOrigin
public class UserController {

    private JdbcUserDao jdbcUserDao;
    private UserDao userDao;

    public UserController(JdbcUserDao jdbcUserDao, UserDao userDao) {
        this.jdbcUserDao = jdbcUserDao;
        this.userDao = userDao;
    }

    @PreAuthorize("permitAll")
    @RequestMapping(path = "/users/{forum_id}", method = RequestMethod.GET)
    public List<User> getUsersForForum(@PathVariable int forum_id) {
        return userDao.getUsersByForumID(forum_id);
    }

    @PreAuthorize("permitAll")
    @RequestMapping(path = "/user/{post_id}", method = RequestMethod.GET)
    public User getUserByPostID(@PathVariable int post_id) {
        return userDao.getUsernameByPostID(post_id);
    }

    @PreAuthorize("permitAll")
    @RequestMapping(path = "/user/comment/{comment_id}", method = RequestMethod.GET)
    public User getUserByCommentID(@PathVariable int comment_id) {
        return userDao.getUsernameByCommentID(comment_id);
    }
}