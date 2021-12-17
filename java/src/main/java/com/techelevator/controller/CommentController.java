package com.techelevator.controller;

import com.techelevator.dao.*;
import com.techelevator.model.Comment;
import com.techelevator.model.Post;
import com.techelevator.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

@RestController
@PreAuthorize("isAuthenticated()")
@CrossOrigin
public class CommentController {
    private CommentDao commentDao;
    private UserDao userDao;

    public CommentController(CommentDao commentDao, UserDao userDao) {
        this.commentDao = commentDao;
        this.userDao = userDao;
    }


        @PreAuthorize("permitAll")
        @RequestMapping(path = "/comment/{post_id}", method = RequestMethod.GET)
        public List<Comment> getCommentsForPost(@PathVariable int post_id) {
            return commentDao.getCommentsByPostID(post_id);
        }

    @RequestMapping(path = "/{post_id}/comments/create-comment", method = RequestMethod.POST)
    public void createComment(Principal principal, @PathVariable int post_id, @RequestBody Comment comment) {
        String username = principal.getName();
        int user_id = userDao.findIdByUsername(username);
        LocalDate comment_datetime = LocalDate.now();
        commentDao.createComment(post_id, null, user_id, 0, 0, comment_datetime, comment.getComment_text());
    }

    @RequestMapping(path = "/{post_id}/{comment_id}/like-update", method = RequestMethod.POST)
    public void updateCommentLikes(Principal principal, @PathVariable int comment_id, @PathVariable int post_id) {
        String username = principal.getName();
        int user_id = userDao.findIdByUsername(username);
        commentDao.updateCommentLikes(comment_id, user_id, post_id);
    }

    @RequestMapping(path = "/{post_id}/{comment_id}/dislike-update", method = RequestMethod.POST)
    public void updateCommentDislikes(Principal principal, @PathVariable int comment_id, @PathVariable int post_id) {
        String username = principal.getName();
        int user_id = userDao.findIdByUsername(username);
        commentDao.updateCommentDislikes(comment_id, user_id, post_id);
    }
    }

