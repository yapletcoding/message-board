<template>
<div class="forum-page">
                <div id="top-middle">
              <div id="middle-left-forum-title">
                  <div id="member-count">
                  </div>
                  <h2 id="forum-name" v-bind:key="forum.forum_id">{{forum.forum_title}}</h2>
                  <h3 v-bind:key="forum.forum_id">{{forum.forum_description}}</h3>
              </div>
              <div id="middle-right-user-tools">
                  <router-link
                  v-bind:to="{ name: 'forum-page', params: { forum_id: forum.forum_id}}">
                  <button v-on:click.prevent="favForum" type="submit">
                      <i class="fas fa-heart"></i></button>
                  </router-link>
              </div>
              </div>  
            <div id="middle-column-posts">
                <div id="post-list">
                    <div id="post">
                        <div id="post1" v-for="post in posts" v-bind:key="post.post_id">
                        <router-link
                        v-bind:to="{ name: 'post-page', params: { post_id: post.post_id}}">
                        {{post.post_title}}
                        </router-link>
                        <h4 id="post-description">{{post.post_text}}</h4>
                        </div>
                        <div id="reaction-buttons-to-post">
                        </div>
                    </div>
                </div>
                <div id="bottom-middle">
                <div id="new-post">
                    <router-link
                    v-bind:to="{ name: 'new-post-component', params: { forum_id: forum.forum_id}}">
                    <input type="text" id="textbox-new-post" placeholder="start a new post!"><button type="submit"><i class="fas fa-pencil"></i></button>
                    </router-link>
                </div>
                <!-- <div id="new-post">
                    <input type="text" id="textbox-new-post" placeholder="start a new post!">
                    <router-link
                    v-bind:to="{ name: 'new-post', params: { forum_id: post.forum_id}}">
                    <input type="submit" value="Create Post" class="fas fa-pencil">
                    </router-link>
                    </div> -->
                </div>
            </div>
                <div id="left-column-membership-info">
                    <div id="member-list">
                        <h3>Member List <i class="fas fa-user"></i></h3>
                        <ul id="member-list-names" v-for="user in users" v-bind:key="user.user_id">
                        <li><i class="far fa-paper-plane"></i> {{user.username}}</li>
                        </ul>
                    </div>
                    <div id="pinned-posts">
                        <h4>Pinned Posts <i class="fas fa-book"></i></h4>
                        <a href="sample.com"> Forum Rules You Must Follow Or Else</a>
                    </div>
                    <div id="mods-list">
                        <h4>Brought to you by the team of:<i class="fas fa-crown"></i></h4>
                        <ul>
                            <li>Jeff</li>
                            <li>Riv</li>
                            <li>Thwin</li>
                            
                            <li>Danny</li>
                        </ul>
                    </div>
                </div>
                <div id="right-column-active-thread">
                    <div id="thread-title"><p>Favorite Forums</p></div>
                    <div id="clicked-post">
                        <FavoriteForums id="favorite-forum" :key="componentKey" @refresh="refreshData">
                        </FavoriteForums>
                    </div>
                </div>
        </div>
</template>
<script>
import forumService from "@/services/ForumService.js";
import FavoriteForums from "@/components/FavoriteForums.vue";
export default {
    name: 'forum-page-component',
    components: {FavoriteForums},
    data() {
    return {
      forum: {},
      users: [],
      posts: [],
      componentKey: 0,
    }
  },
  created() {
    this.fetchForum();
    this.fetchUsers();
    this.fetchPosts();
    },
  methods: {
    fetchForum() {
      forumService.get(this.$route.params.forum_id)
        .then((response) => {
        this.forum = response.data
    })
    },
    fetchUsers() {
        forumService.users(this.$route.params.forum_id)
        .then((response) => {
        this.users = response.data
    })
    },
    fetchPosts() {
        forumService.getPosts(this.$route.params.forum_id)
        .then((response) => {
        this.posts = response.data
    })
    },
    favForum() {
        forumService.favoriteForum(this.$route.params.forum_id)
        this.$emit('refresh');
    },
    refreshData() {
        this.componentKey += 1
    }
}
}
</script>
<style scoped>
.fa-heart {

}
.fa-crown {
    margin: 0 0 0 10px;
}
.red {
    color:red;
}
.fa-paper-plane {
    margin: 0 5px 0 0;
}
#post-list {
    background-color:#606D9E;
    padding: 10px 0 10px 0;
}
#post-description {
    text-align: end;
    background: #DEDEEF;
    font-size: 20px;
    border-bottom: 3px solid gray;
    padding: 10px 0 10px 0;
}
#member-list-names {
    padding: 0 0 0 40px;
    font-size: 20px;
}
#member-list-names:hover {
    text-shadow: 0 0 5px #DEDEEF;
    transform: scale(3.1);
    transform: translateX(5px);
}
#left-column-membership-info {
    grid-area: left-column;
    display: flex;
    flex-direction: column;
    font-size: 15px;
}
#middle-column-posts {
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
    border-left: 2px #E69F97 solid;
    padding-left: 5px;
    grid-row-start: 1;
    grid-row-end: last-line;
}
#forum-name {
    font-size: 35px;
    justify-items: left;
  font-family: 'Raleway', sans-serif;
    font-weight: lighter;
    letter-spacing: 15px;
    transition: .5s;
}
#forum-name:hover {
    color: #DEDEEF;
    text-shadow: 0 0 5px #FFEE10;
    transform: scale(1.1);
}
#middle-left-forum-title {
    justify-items: left;
    font-size: 16px;
  font-family: 'Raleway', sans-serif;
    font-weight: lighter;
    letter-spacing: 15px;
    transition: .5s;
}
#middle-right-user-tools {
    border-radius: 12px;
    background-color:#D49CB4;
    justify-items: right;
    align-self: center;
}
#favorite-forum {
    justify-items: center;
    width: fit-content;
    font-family: Inter, sans-serif;
    margin: 5px 10px;
    font-size: 10px;
    text-align: center;
    border-radius: 12px;
    border-top: 2px solid #999191;
    border-left: 2px solid #999191;
    border-bottom: 2px solid #D3D3D3;
    border-right: 2px solid #D3D3D3;
}
#clicked-post {
    display: flex;
}
#clicked-post:hover {
    background-color: white;
}
#favorite-forum:hover {
    color: white;
  }
#new-post {
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
  #textbox-new-post {
    font-family: Inter, sans-serif;
    margin: 5px 10px;
    font-size: 10px;
    text-align: center;
    padding: 2px;
    border: 3px solid #DEDEEF;
    border-radius: 12px;
    -webkit-transition: all .15s ease-in-out;
    transition: all .15s ease-in-out;
    color: #302D4A;
    outline: #302D4A;
  }
  #textbox-new-post:hover {
    box-shadow: 0 0 1px 0 #D49CB4 inset, 0 0 5px 1px #D49CB4;
    border: 3px solid white;
  }
  #post {
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
  #post1 > a {
      color:#606D9E;
      font-size: 18px;
      background: white;
    margin: 0 10px 5px 0;
    border: solid 2px #949CCC;
    padding: 20px 30px 20px 30px;
    box-shadow: 13px 13px 0 0 #949CCC,
              15px 15px 0 0 #949CCC,
              15px 11px 0 0 #949CCC,
              11px 15px 0 0 #949CCC;
      
  }
  a:link {
    color: rgb(100, 100, 100);
    text-decoration: none;
  }
  a:hover {
      color: white;
      
  }
main {
    background-color: #949CCC;
    margin: 70px 25px 0px 25px;
    font-family: Inter, sans-serif;
    color: white;
}
.forum-page {
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