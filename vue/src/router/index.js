import Vue from 'vue'
import Router from 'vue-router'
import Home from '../views/Home.vue'
import Login from '../views/Login.vue'
import Logout from '../views/Logout.vue'
import Register from '../views/Register.vue'
import store from '../store/index'
import Forum from '../views/Forum.vue'
import ForumView from '../views/ForumView.vue'
import ForumPage from '../components/ForumPage.vue'
import PostView from '../views/PostView.vue'
import PostPage from '../components/PostPage.vue'
import NewPost from '../components/NewPost.vue'
import NewPostView from '../views/NewPostView.vue'
import NewComment from '../components/NewComment.vue'
import NewCommentView from '../views/NewCommentView.vue'
import NewForum from '../components/NewForum.vue'
import NewForumView from '../views/NewForumView.vue'
import Post from '../views/Post.vue'
import ConfirmDelete from '../components/ConfirmDelete.vue'
import ConfirmDeleteView from '../views/ConfirmDeleteView.vue'
Vue.use(Router)

Vue.component('ForumPage', ForumPage)
Vue.component('PostPage', PostPage)
Vue.component('NewPost', NewPost)
Vue.component('NewComment', NewComment)
Vue.component('NewForum', NewForum)
Vue.component('ConfirmDelete', ConfirmDelete)

/**
 * The Vue Router is used to "direct" the browser to render a specific view component
 * inside of App.vue depending on the URL.
 *
 * It also is used to detect whether or not a route requires the user to have first authenticated.
 * If the user has not yet authenticated (and needs to) they are redirected to /login
 * If they have (or don't need to) they're allowed to go about their way.
 */

const router = new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [
    {
      path: '/',
      name: 'home',
      component: Home,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/login",
      name: "login",
      component: Login,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/logout",
      name: "logout",
      component: Logout,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/register",
      name: "register",
      component: Register,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: '/forum',
      name: 'forum',
      component: Forum,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: '/post',
      name: 'post',
      component: Post,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: '/forum/:forum_id',
      name: 'forum-page',
      component: ForumView,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: '/post/:post_id',
      name: 'post-page',
      component: PostView,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: '/:forum_id/post/create-post',
      name: 'new-post-component',
      component: NewPostView,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: '/:post_id/comment/create-comment',
      name: 'new-comment-component',
      component: NewCommentView,
      meta: {
        requiresAuth: true
      }
    },

    {
      path: '/forums/create-forum',
      name: 'new-forum-component',
      component: NewForumView,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: '/post/:post_id/delete',
      name: 'confirm-delete-component',
      component: ConfirmDeleteView,
      meta: {
        requiresAuth: true
      }
    },

  ]
})

router.beforeEach((to, from, next) => {
  // Determine if the route requires Authentication
  const requiresAuth = to.matched.some(x => x.meta.requiresAuth);

  // If it does and they are not logged in, send the user to "/login"
  if (requiresAuth && store.state.token === '') {
    next("/login");
  } else {
    // Else let them go to their next destination
    next();
  }
});

export default router;
