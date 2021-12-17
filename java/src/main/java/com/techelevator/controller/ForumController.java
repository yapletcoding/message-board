package com.techelevator.controller;

import com.techelevator.dao.ForumDao;
import com.techelevator.dao.JdbcForumDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.Forum;
import com.techelevator.model.Message;
import com.techelevator.model.Post;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@RestController
@PreAuthorize("isAuthenticated()")
@CrossOrigin
public class ForumController {
    private JdbcForumDao jdbcForumDao;
    private ForumDao forumDao;
    private UserDao userDao;

    public ForumController(JdbcForumDao jdbcForumDao, ForumDao forumDao, UserDao userDao) {
        this.jdbcForumDao = jdbcForumDao;
        this.forumDao = forumDao;
        this.userDao = userDao;
    }

    @PreAuthorize("permitAll")
    @RequestMapping(path = "/forum", method = RequestMethod.GET)
    public List<Forum> findAllForums(){
        return forumDao.findAll();
    }

    @PreAuthorize("permitAll")
    @RequestMapping(path = "/forum/search/{forum_title}", method = RequestMethod.GET)
    public List<Forum> searchForums(@PathVariable String forum_title) { return forumDao.getForumByPartialName(forum_title);}

    @PreAuthorize("permitAll")
    @RequestMapping(path = "/forum/{forum_id}", method = RequestMethod.GET)
    public Forum browseForum(@PathVariable int forum_id) {
        return forumDao.getForumByForumID(forum_id);
    }

    @PreAuthorize("permitAll")
    @RequestMapping(path = "/forum/post/{post_id}", method = RequestMethod.GET)
    public Forum getForumByPostID(@PathVariable int post_id) {
        return forumDao.getForumByPostID(post_id);
    }

    @PreAuthorize("permitAll")
    @RequestMapping(path = "/top-forums", method = RequestMethod.GET)
    public List<Forum> findTopForums() {return forumDao.getTopForums();}


    @RequestMapping(path = "/forums/create-forum", method = RequestMethod.POST)
    public void createForum(Principal principal, @RequestBody Forum forum) {
        String username = principal.getName();
        int user_id = userDao.findIdByUsername(username);
        int forum_id = forumDao.createForum(forum.getForum_title(), forum.getForum_description(), 1, 0);
        forumDao.createUserForumData(user_id, forum_id);
    }


    @RequestMapping(path = "/forums/{forum_id}/favorite", method = RequestMethod.POST)
    public void favoriteForum(Principal principal, @PathVariable int forum_id) {
        String username = principal.getName();
        int user_id = userDao.findIdByUsername(username);
        forumDao.favoriteForum(user_id, forum_id);
    }

    @RequestMapping(path = "/forum/favorites", method = RequestMethod.GET)
    public List<Forum> findFavoriteForums(Principal principal){
        String username = principal.getName();
        int user_id = userDao.findIdByUsername(username);
        return forumDao.findFavoriteForums(user_id);
    }


    @RequestMapping(path = "/message", method = RequestMethod.GET)
    public Message getMessage() {
        Message message = new Message();
        message.setText("hello there");
        message.setDate(new Date());
        return message;
    }
}
