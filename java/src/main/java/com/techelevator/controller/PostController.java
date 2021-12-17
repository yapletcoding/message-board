package com.techelevator.controller;

import com.techelevator.dao.JdbcPostDao;
import com.techelevator.dao.PostDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.Forum;
import com.techelevator.model.Post;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.security.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@RestController
@PreAuthorize("isAuthenticated()")
@CrossOrigin
public class PostController {
    private PostDao postDao;
    private UserDao userDao;

    public PostController(PostDao postDao, UserDao userDao) {
        this.postDao = postDao;
        this.userDao = userDao;
    }

    @PreAuthorize("permitAll")
    @RequestMapping(path = "/posts/{forum_id}", method = RequestMethod.GET)
    public List<Post> findAllPosts(@PathVariable int forum_id) {return postDao.getPostsByForumId(forum_id);}

    @PreAuthorize("permitAll")
    @RequestMapping(path = "/posts", method = RequestMethod.GET)
    public List<Post> findTopPosts() {return postDao.getTopPosts();}

    @PreAuthorize("permitAll")
    @RequestMapping(path = "/popular-posts/{forum_id}", method = RequestMethod.GET)
    public List<Post> findPopularPosts(@PathVariable int forum_id) {return postDao.getPopularPosts(forum_id);}

    @PreAuthorize("permitAll")
    @RequestMapping(path = "/recent-posts/{forum_id}", method = RequestMethod.GET)
    public List<Post> findRecentPosts(@PathVariable int forum_id) {return postDao.getRecentPosts(forum_id);}

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(path = "/{forum_id}/posts/create-post", method = RequestMethod.POST)
    public void createPost(Principal principal, @PathVariable int forum_id, @RequestBody Post post) {
        String username = principal.getName();
        int user_id = userDao.findIdByUsername(username);
        LocalDate post_datetime = LocalDate.now();
        System.out.println("***************************" + post_datetime + "************************************************");
       postDao.createPost(forum_id, user_id, post.getPost_title(), post.getPost_text(), 0, 0, 0, post_datetime, post.getPost_img());
    }

    @PreAuthorize("permitAll")
    @RequestMapping(path = "/post/{post_id}", method = RequestMethod.GET)
    public Post browsePost(@PathVariable int post_id) {
        return postDao.getPostByPostID(post_id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(path = "/post/{post_id}/delete", method = RequestMethod.DELETE)
    public void deletePost(@PathVariable int post_id) {
        postDao.deletePost(post_id);
    }

    @PreAuthorize("permitAll")
    @RequestMapping(path = "/post/search/{post_title}", method = RequestMethod.GET)
    public List<Post> searchPosts(@PathVariable String post_title) { return postDao.getPostByPartialName(post_title);}

    @PreAuthorize("permitAll")
    @RequestMapping(path = "/post", method = RequestMethod.GET)
    public List<Post> findAllPosts(){
        return postDao.findAllPosts();
    }

    @RequestMapping(path = "/post/{post_id}/like-update", method = RequestMethod.POST)
    public void updatePostLikes(Principal principal, @PathVariable int post_id) {
        String username = principal.getName();
        int user_id = userDao.findIdByUsername(username);
        postDao.updatePostLikes(post_id, user_id);
    }

    @RequestMapping(path = "/post/{post_id}/dislike-update", method = RequestMethod.POST)
    public void updatePostDislikes(Principal principal, @PathVariable int post_id) {
        String username = principal.getName();
        int user_id = userDao.findIdByUsername(username);
        postDao.updatePostDislikes(post_id, user_id);
    }

    @RequestMapping(path = "/my-posts", method = RequestMethod.GET)
    public List<Post> findMyPosts(Principal principal){
        String username = principal.getName();
        int user_id = userDao.findIdByUsername(username);
        return postDao.findMyPosts(user_id);
    }
}
