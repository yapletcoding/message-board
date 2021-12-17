import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'

Vue.use(Vuex)

/*
 * The authorization header is set for axios when you login but what happens when you come back or
 * the page is refreshed. When that happens you need to check for the token in local storage and if it
 * exists you should set the header so that it will be attached to each request
 */
const currentToken = localStorage.getItem('token')
const currentUser = JSON.parse(localStorage.getItem('user'));

if(currentToken != null) {
  axios.defaults.headers.common['Authorization'] = `Bearer ${currentToken}`;
}

export default new Vuex.Store({
  state: {
    token: currentToken || '',
    user: currentUser || {},
    forum: {
      title: '',
    },
    favoriteForums: [],
    myPosts: [],
    forums: [],
    posts: [],
    comment: {},
    post: {},

    // forum: {
    //   id: 0,
    //   title: '',
    //   messages: [],
    //   anything: 'what'
    // }
  },
  mutations: {
    SET_AUTH_TOKEN(state, token) {
      state.token = token;
      localStorage.setItem('token', token);
      axios.defaults.headers.common['Authorization'] = `Bearer ${token}`
    },
    SET_USER(state, user) {
      state.user = user;
      localStorage.setItem('user',JSON.stringify(user));
    },
    LOGOUT(state) {
      localStorage.removeItem('token');
      localStorage.removeItem('user');
      state.token = '';
      state.user = {};
      axios.defaults.headers.common = {};
    },
    SET_FORUM(state, data) {
      state.forum = data;
    },
    SET_FAVORITE_FORUMS(state, data) {
      state.favoriteForums = data;
    },
    SET_MY_POSTS(state, data) {
      state.myPosts = data;
    },
    SET_TOP_POSTS(state, data) {
      state.forums = data;
    },
    SET_TOP_FORUMS(state, data) {
      state.posts = data;
    },
    SET_COMMENT_LIKES(state, data) {
      state.comment = data;
    },
    SET_POST_LIKES(state, data) {
      state.post = data;
    }
  }
})
