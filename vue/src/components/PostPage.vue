<template>
<div class="posts-page">
        
            
                <div id="top-middle">
                    <div id="middle-right-user-tools">
                  
                  <router-link
                  v-bind:to="{ name: 'confirm-delete-component', params: { post_id: post.post_id}}">
                  <input v-on:click="routePush()" v-show="this.$store.state.user.authorities[0].name === 'ROLE_ADMIN'" type="submit" value="Delete" id="back-button">
                  </router-link>
              </div>
              <div id="middle-left-post-title">
                  <div id="member-count">
                  </div>
                  <p id="post-title"
                  >{{post.post_title}}</p>
              </div>

              </div>  
            
            <div id="middle-column-comments">
                <div id="post-body">
                    <p>{{post.post_text}}</p>
                    <div id="post-image">
                    <img :src="post.post_img">
                    <br>
                </div>
                </div> 
                <div id="image-container">
                </div>
                <div id="post-react">
                <PostReactions id="post-reactions" v-bind:key="post.key" v-bind:post="post" @refresh="fetchPost">
                    
                    </PostReactions>
                    </div>
                <div id="comment-list">
                    <Comment id="comment" v-for="comment in comments" v-bind:key="comment.id" v-bind:comment="comment" @refresh="fetchComments">
                    
                    </Comment>
                </div>
                <div id="bottom-middle">
                <div id="new-comment">
                    <router-link
                    v-bind:to="{ name: 'new-comment-component', params: { post_id: post.post_id}}">
                    <button type="text" id="textbox-new-comment" placeholder="click to comment">make a comment <i class="fas fa-pencil"></i></button>
                    </router-link>
                </div>
                </div>
            </div>
                <div id="left-column-poster-info">
                    <div id="thread-title">
                        <p>{{formatDate(post.post_datetime)}}</p>
                    </div>
                    <div id="poster-user-info">
                        <h3>Author <i class="fas fa-user"></i></h3>
                        <h4 id="author-username" v-bind:key="user.user_id"><i class="far fa-paper-plane"></i>{{user.username}}</h4>
                    </div>
                    <div id="pinned-posts">
                        <h4>Current Forum <i class="fas fa-book"></i></h4>
                        <p v-bind:key="forum.forum_id"> {{forum.forum_title}}</p>
                    </div>
                    <div id="back-button-left">
                        <router-link
                  v-bind:to="{ name: 'forum-page', params: { forum_id: post.forum_id}}">
                  <input type="submit" value="Back To Forum" id="back-button">
                  </router-link>
                    </div>
                     <div class="drop"></div>
                     <div class="wave"></div>
                </div>
                <div id="right-column-active-thread">
                    <div id="thread-title"><p>Favorite Forums</p></div>
                    <div id="clicked-post">
                        <FavoriteForums id="favorite-forum" :key="componentKey">

                        </FavoriteForums>
                    </div>
                </div>
                </div>
</template>

<script>
import forumService from "@/services/ForumService.js";
import moment from "moment";
import Comment from "@/components/Comment.vue";
import PostReactions from "@/components/PostReactions.vue";
import FavoriteForums from "@/components/FavoriteForums.vue";
export default {
    
    name: 'post-page-component',
    components: {Comment, PostReactions, FavoriteForums},
    data() {
    return {
      post: {},
      comments: [],
      user: {},
      currentUser: {},
      forum: {},
      isAdmin: "",
      componentKey: 0
    }
    },
    created() {
    this.fetchPost();
    this.fetchComments();
    this.getAuthor();
    console.log(this.user.authorities)
    this.getForum();
    },
    methods: {
    fetchPost() {
      forumService.getPostByID(this.$route.params.post_id)
        .then((response) => {
        this.post = response.data
        this.post.key = new Date() + "post_id" + this.post.post_id
        console.log(this.post.key)
    })
    },
    fetchComments() {
      forumService.getComments(this.$route.params.post_id)
        .then((response) => {
        this.comments = response.data
        this.post.key = new Date() + "comment_id" + Math.random()
    })
    },
    routePush() {
        this.$router.push(`/post/${this.$route.params.post_id}/delete`)
    },
    getAuthor() {
      forumService.authorName(this.$route.params.post_id)
        .then((response) => {
            console.log(response.data)
        this.user = response.data
        console.log(this.$store.state.user.authorities[0].name)
    })
    },
    getForum() {
        forumService.getForumForPost(this.$route.params.post_id)
        .then((response) => {
            console.log(response.data)
        this.forum = response.data
    })
},
    formatDate(date) {
        return moment(date).format('MMMM Do YYYY, h:mm:ss a');
    },

},
}
</script>

<style scoped>
img {
    width: 100%;
    height: 100%;
    overflow: hidden;
}
.fa-paper-plane {
    margin: 0 5px 0 0;
}
.fa-comments {
    margin: 0 10px 0 0;
}
#author-username {
    padding: 0 0 0 40px;
}
#author-username:hover {
    text-shadow: 0 0 5px #DEDEEF;
    transform: scale(3.1);
    transform: translateX(5px);
}
#left-column-poster-info {
    grid-area: left-column;
    display: flex;
    flex-direction: column;
    font-size: 15px;
}
#middle-column-comments {
    grid-area: focus-middle;
    display: flex;
    flex-direction: column;
    justify-content: flex-end;
    font-size: 15px;
    background-color: #DEDEEF;
    grid-row-start: 2;
    grid-row-end: span 3;
    border-radius: 3px;
    color:#302D4A;
    padding: 0 5px 0 10px;
    height: 70vh;
}
#top-middle {
    grid-area: title-middle;
    display: flex;
    flex-flow: row wrap;
    font-size: 20px;
    justify-content: space-between;
    row-gap: 20px;
}
#right-column-active-thread {
    grid-area: right-column;
    display: flex;
    flex-direction: column;
    font-size: 15px;
    border-left: 2px #DEDEEF solid;
    padding-left: 5px;
    grid-row-start: 1;
    grid-row-end: last-line;
}
#middle-left-post-title {
    justify-items: left;
}
#post-title {
  font-size: 25px;
  font-family: 'Raleway', sans-serif;
    font-weight: lighter;
    letter-spacing: 15px;
    transition: .5s;
}
#post-title:hover {
    color: #DEDEEF;
    text-shadow: 0 0 5px #FFEE10;
    transform: scale(1.1);
}
#middle-right-user-tools {
    border-radius: 12px;
    justify-items: right;
    align-self: center;
    justify-content: flex-end;
}
button {
  margin: 5px 0 5px 5px;
  text-align: center;
  color: #606D9E;
  font-weight: 600;
  cursor: pointer;
  border-radius: 5px;
}
#back-button {
    font-size: 16px;
    font-family: Inter, sans-serif;
    text-align: center;
    border-radius: 12px;
    border-top: 3px solid #DEDEEF;
    border-left: 3px solid #DEDEEF;
    border-bottom: 2px solid white;
    border-right: 2px solid white;
    font-size: 16;
    padding: 10px;
    background-color: transparent;
    border-radius: 50px;
    -webkit-transition: all .15s ease-in-out;
    transition: all .15s ease-in-out;
    color: #DEDEEF;
    outline: none;
    cursor: pointer;
}
#back-button:hover {
    box-shadow: 0 0 10px 0 #DEDEEF inset, 0 0 20px 2px #DEDEEF;
    border: 3px solid #DEDEEF;
    background-color: transparent;
    outline:none;
}
#post-body {
    background-color: white;
    overflow: scroll;
    border-radius: 5px;
    margin: 10px 10px 15px 10px;
    padding: 0 10px 0 14px;
}
#post-body:hover {
    line-height: 1.75em;
  border: 3px solid;
  border-image: linear-gradient(to right, #743AD5 0%, #D53A9D 100%);
  border-image-slice: 1;
  border-width: 3px;
}
#image-container {
    width: 100%;
    height: 30px;
    background-color: #DEDEEF;
}
#post-image {

    background-color: #DEDEEF;
    border-radius: 5px;
    width: fit-content;
    height: fit-content;
    max-width: 80%;
    max-height: 70%;
    margin: 10px;
    padding: 10px;
    justify-content: center;
    text-align: center;
}
#post-body > p {
}
#post-body > p::before {
  }
#post-body > p:nth-child(odd) {
}
#post-body > p:nth-child(odd)::before {
    left: 100%; 
    margin-left: -20px;
}
#post-body > p:nth-child(even) {
  padding-right: 0;
}
#post-body > p:nth-child(even)::before {
    right: 100%; 
    margin-right: -20px;
}
#post-body > p:first-child {
  border-top: 0;
  border-top-right-radius:0;
  border-top-left-radius:0;
}
#post-body > p:last-child {
  border-bottom-right-radius:0;
  border-bottom-left-radius:0;
}
#new-comment {
    background-color: #949CCC;
    display: flex;
    justify-content: flex-end;
    align-content: flex-end;
    align-items: center;
    padding: 0 5px 0 2px;
  }
  #bottom-middle {
    display: flex;
    flex-direction: column;
    justify-content: flex-end;
    grid-row-start: 3;
    grid-row-end: last-line;
  }
  #textbox-new-comment {
    background-color: #DEDEEF;
    font-family: Inter, sans-serif;
    font-weight: bold;
    margin: 5px 10px;
    font-size: 16px;
    text-align: center;
    padding: 5px 5px 5px 5px;
    border-top: 3px solid #AA6986;
    border-right: 3px solid #AA6986;
    border-bottom: 3px solid #D79CB9;
    border-left: 3px solid #D79CB9;
    border-radius: 12px;
    -webkit-transition: all .15s ease-in-out;
    transition: all .15s ease-in-out;
    color: #302D4A;
    outline: #302D4A;
    cursor: pointer;
  }
  #textbox-new-comment:hover {
    box-shadow: 0 0 1px 0 #D49CB4 inset, 0 0 5px 1px #D49CB4;
    border: 3px solid white;

  }
  #comment {
    color: #302D4A;
    background-color: transparent;
    font-family: Inter, sans-serif;
    margin: 5px 10px 5px 10px;
    display: flex;
    flex-direction: column;
    justify-content: flex-start;
    grid-row-start: 1;
    grid-row-end: 2;
  }
  #comment1, #comment2, #comment3, #comment4, #comment5, #comment6, #comment7, #comment8, #comment9, #comment10 {
    background: white;
    margin: 0 10px 5px 0;
    border: solid 2px #949CCC;
    padding: 20px 30px 20px 30px;
    box-shadow: 13px 13px 0 0 #949CCC,
              15px 15px 0 0 #949CCC,
              15px 11px 0 0 #949CCC,
              11px 15px 0 0 #949CCC;
  }
#comment-list {
    overflow: scroll;
}
a:link {
    color: #949CCC;
    text-decoration: none;
}
a:hover {
    color: white;
}
.posts-page {
        color: white;
        background-color: #136A8A;
        background: -webkit-linear-gradient(to right, #B66674, #606D9E);
        background: linear-gradient(to right, #B66674, #606D9E);
        box-shadow: 
        0px 2px 10px rgba(0,0,0,0.2), 
        0px 10px 20px rgba(0,0,0,0.3), 
        0px 30px 60px 1px rgba(0,0,0,0.5);
        font-family: Raleway, sans-serif;
        font-weight: bold;
        padding: 10px 10px 20px 10px;
        border-radius: 20px;
        border-top: 2px white solid;
        border-right: 2px white solid;
        border-bottom: 2px #DEDEEF solid;
        border-left: 2px #DEDEEF solid;
        display: grid;
        grid-template-columns: 1fr 60% 1fr;
        column-gap: 10px;
        row-gap: 0px;
        grid-template-areas:
        "left-column title-middle right-column"
        "left-column focus-middle right-column"
        "left-column focus-middle right-column";
}
</style>