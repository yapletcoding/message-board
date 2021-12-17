<template>
<div class = "post-search">
  <div class="post-list">
    <table>
      <thead>
        <tr>
          <th>Posts</th>
            <div id="search-forum-list">
              <form name="searchForForums" id="search-forums-box" v-on:submit.prevent="searchPosts(desiredPost)">
                <input v-model="desiredPost" type="text" name="post-search" id="search-button" placeholder="Search Posts">
                <input type="submit" id="button" for="searchForForums">
                <form name="clearForum" v-on:submit.prevent="fetchPosts()">
                <input type="submit" id="button" value="Clear" for="clearForum">
                </form>
              </form>
            </div>
        </tr>
      </thead>
      <tbody id="table-body">
        <tr class="row" v-for="topic in post" v-bind:key="topic.post_id">
          <td width="80%">
            <router-link
            v-bind:key="topic.post_id"
            v-bind:to="{ name: 'post-page', params: { post_id: topic.post_id}}">
            {{topic.post_title}}
            </router-link>
            <!--v-on-click method to follow path-->
              <!-- v-bind:to="{ name: 'Posts', params: { id: forum.id } }" -->
          </td>
        </tr>
      </tbody>
    </table>
  </div>
  <div id="fav-posts">
    <h1>My Posts</h1>
  <div id = "my-post-list">
    <MyPosts id="my-posts">
      </MyPosts>
    </div>
  </div>
  </div>
</template>
<script>
import MyPosts from "@/components/MyPosts.vue";
import forumService from "@/services/ForumService.js";
export default {
  name: "post-list",
  components: {MyPosts},
  data() {
    return {
      post: [],
      desiredPost: '',
    }
  },
  created() {
    this.fetchPosts();
    // console.log("created"); debug tool
    },
  methods: {
    fetchPosts() {
      // console.log("fetched: ", forumService); debug tool
      forumService.listPosts()
        .then((response) => {
        // console.log(response.data); debug tool
        this.post = response.data
        this.desiredPost = '';
    })
    },
    searchPosts(desiredPost) {
      console.log("searched: ", forumService);
      forumService.searchPosts(desiredPost)
      .then((response) => {
        this.post = response.data
      })
    },
  //   resetForm(event){
  //     if(event.target.value.trim().length === 0) {
  //     this.response = null;
  //   }
  // },
  }
  // computed: {
  //   resetForm: function() {
  //     if(this.desiredForum === ''){
  //       return this.forum;
  //     }
  //   }
  // }
  }
</script>
<style scoped>
h1 {
  color:white;
  font-weight: bolder;
  letter-spacing: 40px;
}
#button {
  text-align: center;
  color: #000;
  text-transform: uppercase;
  font-weight: 600;
  cursor: pointer;
}
button:focus {
  outline: none;
}
#search-forums-box {
  border: none important!;
  padding: 30px 0 30px 0;
  text-align: center;
  color: #000;
  text-transform: uppercase;
  font-weight: 600;
  cursor: pointer;
  border-radius: 5px;
  width: 210px;
}
th {
  font-size: 40px;
   font-family: 'Raleway', sans-serif;
    font-weight: bolder;
    letter-spacing: 40px;
    transition: .5s;
    color: white;
    text-align: center;
}
#search-button {
  margin: 5px 10px;
    font-size: 16px;
    text-align: center;
    padding: 2px;
    border-radius: 12px;
    -webkit-transition: all .15s ease-in-out;
    transition: all .15s ease-in-out;
    color: #302D4A;
    outline: #606D9E;
    border-bottom: 2px solid #606D9E;
    border-left: 2px solid #606D9E;
    border-top: 2px solid #302D4A;
    border-right: 2px solid #302D4A;
}
#search-button:hover {
  box-shadow: 0 0 1px 0 #D49CB4 inset, 0 0 5px 1px #D49CB4;
}
#search-forum-list {
  font-size: 18px;
  background-color: transparent;
  outline: none;
  border: none;
  display: flex;
  flex-direction: row;
}
#search-forum-list:hover {
  border: transparent;
  border-radius: 5px;
}
.post-list {
  font-style: bold;
  grid-area: post-list;
  margin: 0 auto;
  max-width: 800px;
  color: white;
  background-color: #136A8A;
  background: -webkit-linear-gradient(to right, #B66674, #606D9E);
  background: linear-gradient(to right, #B66674, #606D9E);
  box-shadow: 
    0px 2px 10px rgba(0,0,0,0.2), 
    0px 10px 20px rgba(0,0,0,0.3), 
    0px 30px 60px 1px rgba(0,0,0,0.5);
  padding: 80px 50px 50px 50px;
  border-radius: 10px;
  justify-content: center;
  justify-items: center;
}
#my-post-list {
  display: flex;
  flex-direction: column;
  max-width: 200px;
}
#my-post-list > a {
  max-width: 200px;
  display: flex;
  flex-direction: column;
  
}
#my-posts {
  max-width: 150px;
}
#my-posts > a {
  max-width: 150px;
  
}
#fav-posts{
  grid-area: fav-posts;
  background-color: #B66674;
 background: -webkit-linear-gradient(to right, #606D9E,  #B66674);
  background: linear-gradient(to right, #606D9E,  #B66674);
 display: flex;
 flex-direction: column;
 width: 550px;
 border-top: 1px solid #D49CB4;
 border-right: 1px solid #D49CB4;
 border-bottom: 1px solid #302D4A;
 border-left: 1px solid #302D4A;
 border-radius: 12px;
 padding: 5% 5% 5% 5%;
 overflow: scroll;
  box-shadow: 
    0px 2px 10px rgba(0,0,0,0.2), 
    0px 10px 20px rgba(0,0,0,0.3), 
    0px 30px 60px 1px rgba(0,0,0,0.5);
  padding: 50px 50px 50px 50px;
}
.post-search {
  margin: 15px 0 0 0;
  display: grid;
  grid-template-columns: 30% 30% 1fr;
  grid-template-areas: 
  "post-list post-list fav-posts";
}
.forum {
  font-size: 24px;
  border-bottom: 1px solid #F2F2F2;
}
.forum:last-child {
  border: 0px;
}
table {
  width: 800px;
  border-collapse: collapse;
}
td {
}
tbody tr:nth-child(even) {
  background-color: #F2F2F2;
  padding: 5px 0 0 10px;
}
tbody tr:nth-child(odd) {
  background-color: #DEDEEF;
  padding: 5px 0 0 10px;
}
#table-body {
 color: red;
 background-color: #B66674;
 background: -webkit-linear-gradient(to right, #F2F2F2,  #DEDEEF);
  background: linear-gradient(to right, #F2F2F2,  #DEDEEF);
 display: flex;
 flex-direction: column;
 width: 80%;
 border-top: 1px solid #D49CB4;
 border-right: 1px solid white;
 border-bottom: 1px solid #302D4A;
 border-left: 1px solid #302D4A;
 border-radius: 12px;
 padding: 5% 5% 5% 5%;
 overflow: scroll;
}
.post-list a:link,
.post-list a:visited {
  color: #606D9E;
  text-decoration: none;
  font-weight: bold;
}
.post-list a:hover {
  text-decoration: none;
  color: #AA6986;
}
.row {
  width: 80%;
  border: none;
  border-radius: 12px;
}
thead tr {
  display: flex;
  justify-content: space-between;
  width: 90%;
}
</style>