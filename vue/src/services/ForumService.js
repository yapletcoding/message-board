import axios from 'axios';

const baseURL = "http://localhost:8080";

const ForumService = {
  list: function() {
    return axios.get(baseURL + '/forum');
  },

  listPosts: function() {
    return axios.get(baseURL + '/post');
  },

  get: function(id) {
    return axios.get(`/forum/${id}`);
  },

  search: function(forum_title) {
    return axios.get(baseURL + `/forum/search/${forum_title}`);
  },

  users: function(id) {
    return axios.get(baseURL + `/users/${id}`);
  },

  topPosts: function() {
    return axios.get(baseURL + `/posts`);
  },

  topForums: function() {
    return axios.get(baseURL + `/top-forums`);
  },

  getPosts: function(forum_id) {
    return axios.get(baseURL + `/posts/${forum_id}`);
  },

  getComments: function(post_id) {
    return axios.get(baseURL + `/comment/${post_id}`);
  },

  getPostByID: function(post_id) {
    return axios.get(baseURL + `/post/${post_id}`);
  },

  postPost: function(forum_id, newPost){
    return axios.post(baseURL + `/${forum_id}/posts/create-post`, newPost)
  },

  postComment: function(post_id, newComment){
    return axios.post(baseURL + `/${post_id}/comments/create-comment`, newComment)
  },

  postForum: function(newForum) {
    return axios.post(baseURL + `/forums/create-forum`, newForum)
  },

  updateCommentLikes: function(post_id, comment_id, comment) {
    return axios.post(baseURL + `/${post_id}/${comment_id}/like-update`, comment)
  },

  updateCommentDislikes: function(post_id, comment_id, comment) {
    return axios.post(baseURL + `/${post_id}/${comment_id}/dislike-update`, comment)
  },

  updatePostLikes: function(post_id, post) {
    return axios.post(baseURL + `/post/${post_id}/like-update`, post)
  },

  updatePostDislikes: function(post_id, post) {
    return axios.post(baseURL + `/post/${post_id}/dislike-update`, post)
  },

  deletePost: function(post_id){
    return axios.delete(baseURL + `/post/${post_id}/delete`)
  },

  searchPosts: function(post_title) {
    return axios.get(baseURL + `/post/search/${post_title}`);
  },

  favoriteForum: function(forum_id, forum) {
    return axios.post(baseURL + `/forums/${forum_id}/favorite`, forum);
  },

  favoriteList: function() {
    return axios.get(baseURL + `/forum/favorites`);
  },

  authorName: function(post_id) {
    return axios.get(baseURL + `/user/${post_id}`);
  },

  commenterName: function(comment_id) {
    return axios.get(baseURL + `/user/comment/${comment_id}`);
  },

  getForumForPost: function(post_id){
    return axios.get(baseURL + `/forum/post/${post_id}`);
  },

  getMyPosts: function() {
    return axios.get(baseURL + `/my-posts`);
  }
}

export default ForumService;