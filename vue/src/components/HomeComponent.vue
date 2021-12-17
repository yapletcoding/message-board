<template>
    <div class="home">
        
        <header>
          <div id="upper-left">
          <div id="logo">
          </div>
          <!-- <div id="create">
              <button @click="refreshData()">Test
              </button>
          </div> -->
          </div>
          <div id="upper-right">
          <div id="user-tools">
            <input type="search" id="search" placeholder="search...">
            <i class="fas fa-envelope-open-text"></i>
            <i class="fas fa-bell"></i>
            <i class="fas fa-user"></i>
          </div>
          </div>
          </header>
          <main>
              <div id="banner">
                  <h1 id="home-banner">DEEP DIVE</h1>
              </div>
              <div id="forums">
                  <div id="top-forums">
                      <h2 class="titles">TOP FORUMS</h2>
                  </div>
                  <div id="top5">
                      <Forum id="forum" v-for="forum in forums" v-bind:key="forum.id" v-bind:forum="forum" @refresh="refreshData">
                    
                    </Forum>
                  </div>
                  <div id="create-forum">
                      <router-link
                    v-bind:to="{ name: 'new-forum-component'}">
                      <button id="cf">CREATE FORUM<i class="fas fa-plus"></i></button>
                      </router-link>
                      <router-link 
                        v-bind:to="{ name: 'forum'}">
                      <button id="sf">SEARCH FORUMS<i class="fas fa-search"></i></button>
                      </router-link>
                  </div>
                  </div>
              <div id="posts">
                  <div id="top-posts">
                      <h2 class="titles">TOP POSTS</h2>
                  </div>
                  <div id="top10">
                      <div id="pi1" v-for="post in posts" v-bind:key="post.post_id">
                        <router-link
                        v-bind:to="{ name: 'post-page', params: { post_id: post.post_id}}">{{post.post_title}}</router-link>
                        <p>{{post.post_text}}</p>
                      </div>
                  </div>
                  <div id="create-post">
                      <router-link
                    v-bind:to="{ name: 'new-post-component'}">
                      <!-- plus icon -->
                      <button id="cp">CREATE POST<i class="fas fa-plus"></i></button>
                      </router-link>
                      <router-link 
                        v-bind:to="{ name: 'post'}">
                      <button id="sp">SEARCH POSTS<i class="fas fa-search"></i></button> 
                      </router-link>
                  </div>
                  </div>
              <div id="my-forums">
                <div id="my-top-forums">
                      <h3 class="titles">FAVORITE FORUMS</h3>
                  </div>
                  <div id="forum-list">
                    <FavoriteForums id="favorite-forum" :key="componentKey">

                        </FavoriteForums>
                    </div>
              </div>
              <div id="my-posts">
              <div id="my-top-posts">
                    <h3 class="titles">MY POSTS</h3>
                </div>
                <div id="my-top10">
                    <MyPosts id="my-posts">

                    </MyPosts>
                </div>
            </div>
          </main>
      <footer></footer>
      </div>
</template>

<script>
import forumService from "@/services/ForumService.js";
import MyPosts from "@/components/MyPosts.vue";
import Forum from "@/components/Forum.vue";
import FavoriteForums from "@/components/FavoriteForums.vue";
export default {
    name: 'home-component',
    components: {Forum, FavoriteForums, MyPosts},
    data() {
        return {
        forums: [],
        posts: [],
        myPosts: [],
        componentKey: 0
    }
    },
created() {
    this.fetchForums();
    this.fetchTopPosts();
    },
  methods: {
    fetchForums() {
      forumService.topForums()
        .then((response) => {
        this.forums = response.data
    })
    },
    fetchTopPosts() {
      forumService.topPosts()
        .then((response) => {
        this.posts = response.data
    })
    },
    fetchMyPosts() {
      forumService.getMyPosts()
        .then((response) => {
        this.myPosts = response.data
    })
    },
    favForum() {
        forumService.favoriteForum(this.$route.params.forum_id)
    },
    refreshData() {
        this.componentKey += 1
    }

}
}


</script>


<style>
* {
    box-sizing: border-box;
    font-family: 'Raleway', sans-serif;
    /* font-family: 'Inter', sans-serif;  */
}
.fa-search {
    padding: 0 0px 0 5px;
    margin: 2px 0px 0 5px;
}
.fa-plus {
    padding: 0 0px 0 5px;
    margin: 2px 0px 0 5px;
}
.fa-envelope-open-text {
    color: #606D9E;
    padding: 5px;
    text-shadow: 0 -1px white, 0 1px #302D4A, 1px 0 #302D4A, 0 -1px #302D4A;
}
.fa-bell {
    color: #AA6986;
    padding: 5px;
    text-shadow: -1px 0 white, 0 1px #302D4A, 1px 0 #302D4A, 0 -1px #302D4A;
}
.fa-user {
    color: #846B93;
    padding: 5px;
    text-shadow: -1px 0 white, 0 1px #302D4A, 1px 0 #302D4A, 0 -1px #302D4A;
}

#home-banner {
    font-family: 'Raleway', sans-serif;
    font-weight: lighter;
    letter-spacing: 40px;
    transition: .5s;
}
#home-banner:hover {
    color: #DEDEEF;
    text-shadow: 0 0 5px #ffee10;
    transform: scale(1.1);
}
header {
    grid-area: header;
    background-color:  #DEDEEF;
    border-top: 3px solid #DEDEEF;
    border-right: 3px solid #DEDEEF;
    border-left: 3px solid #DEDEEF;
}

#logo img {
   font-size: 16px; 
   width: 100px;
   height: 50px;
}

#create {
    font-size: 16px;
}

#user-tools {
   font-size: 16px;
}

#search {
  font-size: 16px;
  padding: 10px;
  margin: 15px;
  background-color: transparent;
  border: 3px solid white;
  border-radius: 50px;
  -webkit-transition: all .15s ease-in-out;
  transition: all .15s ease-in-out;
  color: white;
  outline: none;
}
#search:hover {
  box-shadow: 0 0 10px 0 #D49CB4 inset, 0 0 20px 2px #D49CB4;
  border: 3px solid white;
}
body {
    font-size: 16px;
}

#banner {
    grid-area: banner;
}

#forums {
    grid-area: forums;
    display: flex;
    flex-direction: column;
}

#top-forums {
    font-size: 16px;
    text-align: center;
}
li {
    list-style: none;
}
#top5 {
   font-size: 16px;
   float: right;
    height:1000px;
    width: 500px;
    overflow:scroll;
    border-radius: 12px;
    border-top: 2px white solid;
    border-right: 2px white solid;
    border-left: 2px #606D9E solid;
    background-color: #DEDEEF;
    box-shadow:
     rgba(0, 0, 0, 0.16) 0px 3px 6px, 
     rgba(0, 0, 0, 0.23) 0px 3px 6px;
     color: #302D4A;
}
#create-forum {
    display: flex;
    justify-content: space-between;
}

#cf {
  font-size: 16px;
  padding: 10px 15px 10px 15px;
  margin: 15px;
  background-color: transparent;
  border: 3px solid #DEDEEF;
  border-radius: 50px;
  -webkit-transition: all .15s ease-in-out;
  transition: all .15s ease-in-out;
  color: #DEDEEF;
  display: flex;
  justify-items: space-between;
  justify-content: space-between;
  align-items: space-between;
}
#cf:hover {
  box-shadow: 0 0 10px 0 #DEDEEF inset, 0 0 20px 2px #DEDEEF;
  border: 3px solid #DEDEEF;
}

#sf {
  font-size: 16px;
  padding: 10px 15px 10px 15px;
  margin: 15px;
  background-color: transparent;
  border: 3px solid #DEDEEF;
  border-radius: 50px;
  -webkit-transition: all .15s ease-in-out;
  transition: all .15s ease-in-out;
  color: #DEDEEF;
}
#sf:hover {
  box-shadow: 0 0 10px 0 #DEDEEF inset, 0 0 20px 2px #DEDEEF;
  border: 3px solid #DEDEEF;
}

#sp {
  font-size: 16px;
 padding: 10px 15px 10px 15px;
  margin: 15px;
  background-color: transparent;
  border: 3px solid #DEDEEF;
  border-radius: 50px;
  -webkit-transition: all .15s ease-in-out;
  transition: all .15s ease-in-out;
  color: #DEDEEF;
}
#sp:hover {
  box-shadow: 0 0 10px 0 #DEDEEF inset, 0 0 20px 2px #DEDEEF;
  border: 3px solid #DEDEEF;
}

#search-forums {
    font-size: 16px;
  padding: 10px 15px 10px 15px;
  margin: 15px;
  background-color: transparent;
  border: 3px solid #DEDEEF;
  border-radius: 50px;
  -webkit-transition: all .15s ease-in-out;
  transition: all .15s ease-in-out;
  color: white;
  outline: none;
}
#search-forums:hover {
  box-shadow: 0 0 10px 0 #DEDEEF inset, 0 0 20px 2px #DEDEEF;
  border: 3px solid #DEDEEF;
}

#posts {
    grid-area: posts;
    display: flex;
    flex-direction: column;
    color: black;
    display: flex;
    justify-items: center;
    
}

#top-posts {
    font-size: 16px;
    text-align: center;
    color:#DEDEEF;
}

#top10 {
    font-size: 16px;
    float: right;
    height:1000px;
    width: 500px;
    overflow:scroll;
    grid-area: top10;
    border-radius: 20px;
    border: 2px white solid;
    background-color:  rgb(68, 68, 150);
    box-shadow: 
    rgba(0, 0, 0, 0.16) 0px 3px 6px, 
    rgba(0, 0, 0, 0.23) 0px 3px 6px;
}

#create-post {
    display:flex;
    justify-content: space-between;
}

#cp {
  font-size: 16px;
  padding: 10px;
  margin: 15px;
  background-color: transparent;
  border: 3px solid #DEDEEF;
  border-radius: 50px;
  -webkit-transition: all .15s ease-in-out;
  transition: all .15s ease-in-out;
  color: #DEDEEF;
}
#cp:hover {
  box-shadow: 0 0 10px 0 #DEDEEF inset, 0 0 20px 2px #DEDEEF;
  border: 3px solid #DEDEEF;
}

#search-posts{
  font-size: 16px;
  padding: 10px;
  margin: 15px;
  background-color: transparent;
  border: 3px solid #DEDEEF;
  border-radius: 50px;
  -webkit-transition: all .15s ease-in-out;
  transition: all .15s ease-in-out;
  color: white;
  outline: none;
}
#search-posts:hover {
  box-shadow: 0 0 10px 0 #DEDEEF inset, 0 0 20px 2px #DEDEEF;
  border: 3px solid #DEDEEF;
}
#my-forums {
    grid-area: my-forums;
    display: flex;
    flex-direction: column;
    text-align: center;
    width: fit-content;
    justify-content: center;
}
#my-top5 {
    width: 70%;
}
#my-top10 {
    border-radius: 12px;
    border: 2px #e69f97 solid;
    background-color:  #DEDEEF;
    height:50%;
    width: 500px;
    overflow:scroll;
    box-shadow: 
    rgba(0, 0, 0, 0.16) 0px 3px 6px, 
    rgba(0, 0, 0, 0.23) 0px 3px 6px;
}

#forum-list {

    border-radius: 12px;
    background-color:  #DEDEEF;
    border: 2px #DEDEEF solid;
    height:450px;
    width: 500px;
    overflow:scroll;
    box-shadow:
     rgba(0, 0, 0, 0.16) 0px 3px 6px, 
     rgba(0, 0, 0, 0.23) 0px 3px 6px;
     display: flex;
     flex-direction: row;
}
#favorite-forum > #pi1 {
    width: fit-content;
    max-width: 500px;

}
#favorites {
    width: 500px;
}
#favorites > #pi1 {
    width: 20px;
    height: 30%;
    padding: 5px 0 5px 0;
   
}
#my-top5 > #pi1 {
    color: #302D4A;
    width: 100%;
    height: 30%;
    padding: 5px 0 5px 0;
}
#my-top5 > #pi1:hover {
    color: white;
    width: 100%;
    height: 30%;
    padding: 5px 0 5px 0;
}
#my-posts {
    border-radius: 12px;
    grid-area: my-posts;
    display: flex;
    flex-direction: column;
    text-align: center;
    width: fit-content;
    background-color: none;
}

#upper-left {
    grid-area: upper-left;
    display: flex;  
}

#upper-right {
    grid-area: upper-right;
    display: flex;
    justify-content: right;
}

footer {
    grid-area: footer;
    display: flex;
}

main {
    grid-area: main;
    background-color: #dbdbdb;
    
    border-right: 2px solid #DEDEEF;
    border-bottom: 2px solid #DEDEEF;
    border-left: 2px solid #DEDEEF;
    font-family: 'Raleway', sans-serif;
}

.post-image{
    height: 200px;
    width: 200px;
}

.forum-image{
    height: 200px;
    width: 200px;
}

#p1 {
    grid-area: p1;
    border-bottom: 2px solid #949CCC;
}
#p2 {
    grid-area: p2;
    border-bottom: 2px solid #6768AB;
}
#p3 {
    grid-area: p3;
    border-bottom: 2px solid #AA6986;
}
#p4 {
    grid-area: p4;
    border-bottom: 2px solid #D79CB9;
}
#p5 {
    grid-area: p5;
    border-bottom: 2px solid #D49CB4;
}
#p6 {
    grid-area: p6;
    border-bottom: 2px solid #949CCC;
}
#p7 {
    grid-area: p7;
    border-bottom: 2px solid #6768AB;
}
#p8 {
    grid-area: p8;
    border-bottom: 2px solid #AA6986;
}
#p9 {
    grid-area: p9;
    border-bottom: 2px solid #D79CB9;
}
#p10 {
    grid-area: p10;
    border-bottom: 2px solid #D49CB4;
}


#pi2 {
    grid-area: pi2;
    border-bottom: 2px solid #6768AB;
}
#pi3 {
    grid-area: pi3;
    border-bottom: 2px solid #AA6986;
}
#pi4 {
    grid-area: pi4;
    border-bottom: 2px solid #D79CB9;
}
#pi5 {
    grid-area: pi5;
    border-bottom: 2px solid #D49CB4;
}
#pi6 {
    grid-area: pi6;
    border-bottom: 2px solid #949CCC;
}
#pi7 {
    grid-area: pi7;
    border-bottom: 2px solid #6768AB;
}
#pi8 {
    grid-area: pi8;
    border-bottom: 2px solid #AA6986;
}
#pi9 {
    grid-area: pi9;
    border-bottom: 2px solid #D79CB9;
}
#pi10 {
    grid-area: pi10;
    border-bottom: 2px solid #D49CB4;
}

#f1 {
    grid-area: f1;
    border-bottom: 2px solid #949CCC;
}
#f2 {
    grid-area: f2;
    border-bottom: 2px solid #6768AB;
}
#f3 {
    grid-area: f3;
    border-bottom: 2px solid #AA6986;
}
#f4 {
    grid-area: f4;
    border-bottom: 2px solid #D79CB9;
}
#f5 {
    grid-area: f5;
    border-bottom: 2px solid #D49CB4;
}

#fi1 {
    display: flex;
    flex-direction: column;
    justify-content: center;
    justify-items: center;
    align-items: center;
    align-content: center;
    grid-area: fi1;
    border-width: 3px;
    border-style: solid;
    border-image: 
    linear-gradient(
      to bottom, 
      #AA6986,
      #6768AB
    ) 1 100%;
    color:#302D4A important!;
    text-align: center;
    margin: 10px 0px 10px 10px;
    background-color: #DEDEEF;
    padding: 60px 30px 30px 30px;
    border-radius: 12px;
    width: 500px;
}
#fi1:hover {
    grid-area: fi1;
    border: 2px solid #302D4A;
    color:white important!;
    background-color: #949CCC;
    font-size: 18px;
    letter-spacing: 6px;
    transition: .25s;
}
#fi1 > a {
    color:#DEDEEF;
    text-shadow: 0 0 3px #B66674;
    transform: scale(1.1);
    background-color: white;
    border-bottom: 3px solid white;
    border-left: 3px solid white;
    border-right: #B66674;
    border-top: 3px solid #B66674;
    border-radius: 5px;
    padding: 5px 5px 5px 5px;
    width: fit-content;
    text-transform: uppercase;
    background: -webkit-linear-gradient(to right, #B66674, #606D9E);
    background: linear-gradient(to right, #B66674, #606D9E);
}
#fi1> a:hover {
    color: white;
    text-shadow: 0 0 5px #ffee10;
    transform: scale(1.1);
}
#fi2 {
    grid-area: fi2;
    border-bottom: 2px solid #6768AB;
}
#fi3 {
    grid-area: fi3;
    border-bottom: 2px solid #AA6986;
}
#fi4 {
    grid-area: fi4;
    border-bottom: 2px solid #D79CB9;
}
#fi5 {
    grid-area: fi5;
    border-bottom: 2px solid #D49CB4;
}

.titles {
  align-content: center;
  justify-content: center;
  align-items: center;
  align-content: center;
  font-weight: 560;
}


@media screen and (min-width: 1025px){
    header{
        border-top-left-radius: 12px;
        border-top-right-radius: 12px;
        display: grid;
        grid-template-columns: 1fr 1fr 1fr;
        column-gap: 50px;
        row-gap: 20px;
        align-items: center;
        grid-template-areas: 
        "upper-left . upper-right"
        "main main main";
    }

    main{
        color: white;
        background-color: #136a8a;
        background: -webkit-linear-gradient(to right, #B66674, #606D9E);
        background: linear-gradient(to right, #B66674, #606D9E);
        box-shadow: 
        0px 2px 10px rgba(0,0,0,0.2), 
        0px 10px 20px rgba(0,0,0,0.3), 
        0px 30px 60px 1px rgba(0,0,0,0.5);
        padding: 50px 50px 50px 50px;
        border-bottom-left-radius: 12px;
        border-bottom-right-radius: 12px;
        
        display: grid;
        grid-template-columns: 1fr 1fr 1fr;
        column-gap: 50px;
        row-gap: 20px;
        align-items: top;
        justify-items: center;
        grid-template-areas: 
            "banner banner banner"
            "forums posts my-forums"
            "forums posts my-posts"
            "footer footer footer";
    }

    footer {
        display: grid;
        grid-template-columns: 1fr 1fr 1fr;
        column-gap: 50px;
        row-gap: 20px;
        align-items: center;
        grid-template-areas: 
            "footer footer footer";
    }

    #top10{
        display: flex;
        flex-direction: column;
        column-gap: 10px;
        row-gap: 5px;
        align-items: center;
        color: #302D4A;
        background-color: #DEDEEF;
        text-align: center;
        padding: 10px 5px 0px 5px;
    }
    #top10:hover {
     border: 3px solid #606D9E;
    }

    #top5{
        width: 500px;
        display: flex;
        flex-direction: column;
        column-gap: 10px;
        row-gap: 5px;
        align-items: center;
        color: #302D4A;
        background-color: #DEDEEF;
        text-align: center;
        padding: 10px 5px 0px 5px;
    }
        
}
 #top5:hover {
     border: 3px solid #606D9E;
 }
/* ::-webkit-scrollbar {
  width: 10px;
}
::-webkit-scrollbar-track {
  background: #f1f1f1;
}
::-webkit-scrollbar-thumb {
  background: #888;
}
::-webkit-scrollbar-thumb:hover {
  background: #555;
} */
::-webkit-scrollbar {
  width: 18px;
}
::-webkit-scrollbar-track {
  background-color: transparent;
}
::-webkit-scrollbar-thumb {
  background-color: #846B93;
  border-radius: 12px;
  border: 6px solid transparent;
  background-clip: content-box;
}
::-webkit-scrollbar-corner {
    color: transparent;
    background-color: none;
}

#pi1 {
    font-size: 16px;
    grid-area: pi1;
    color: #302D4A;
    display: flex;
    flex-direction: column;
    justify-content: center;
    justify-items: center;
    align-content: center;
    grid-area: fi1;
    border-width: 3px;
    border-style: solid;
    border-image: 
    linear-gradient(
      to bottom, 
      #AA6986,
      #8c8daf
    ) 1 100%;
    color:#302D4A important!;
    text-align: center;
    margin: 5px 0px 10px 10px;
    background-color: white;
    padding: 20px 30px 30px 30px;
    border-radius: 12px;
    width: 410px;
    font-weight: bold;
}
#my-posts.mine > di1 {
    color:red
}
#pi1:hover {
    grid-area: fi1;
    border: 2px solid #302D4A;
    color:white important!;
    background-color: #cccce9;
    font-size: 18px;
    letter-spacing: 6px;
    transition: .25s;
    cursor: pointer;
}
#pi1 > a {
    font-size: 16px;
    color:#DEDEEF;
    transform: scale(1.1);
    background-color: #b8bdda;
    border-bottom: 3px solid white;
    border-left: 3px solid white;
    border-right: #B66674;
    border-top: 3px solid #B66674;
    border-radius: 5px;
    padding: 5px 5px 5px 5px;
    text-transform: uppercase;
    background: -webkit-linear-gradient(to right, #B66674, #606D9E);
    background: linear-gradient(to right, #B66674, #606D9E);
} 
#pi1 > a:hover {
    color: white;
    transform: scale(1.1);
    background-color: #b6bbd6;
    opacity: .6;
}

</style>