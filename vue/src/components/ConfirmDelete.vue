<template>
  <div id="container" class="new-post">
  <div id="register" class="text-center">
    <form class="form-register">
      <h1 class="h3 mb-3 font-weight-normal">Are you sure you want to delete the following post?</h1>
      <h2 class="h3 mb-3 font-weight-normal" font-style="italic">"{{post.post_title}}"</h2>
      <label for="username" class="sr-only"></label>
      <router-link
        v-bind:to="{ name: 'forum-page', params: { forum_id: post.forum_id}}">
      <button class="btn btn-lg btn-primary btn-block" type="submit" id="create-account" v-on:click="deletePost()">Delete</button>
      </router-link>
      <router-link
        v-bind:to="{ name: 'post-page', params: { post_id: post.post_id}}">
        <button class="btn btn-lg btn-primary btn-block" type="submit" id="create-account" v-on:submit.prevent="cancelDelete()">Cancel</button>
        </router-link>
    </form>
  </div>
  </div>
</template>

<script>
import forumService from "@/services/ForumService.js";

export default {
  name: 'confirm-delete-component',
  data() {
    return {
      post: {},
      forum: {},
      }
    },
    created() {
    this.fetchPost();
    },
  methods: {
      fetchPost() {
          forumService.getPostByID(this.$route.params.post_id)
            .then((response) => {
        this.post = response.data
          })
      },
      getForum() {
        forumService.getForumForPost()
        .then((response) => {
        this.forum = response.data
        })
      },
      deletePost() {
        forumService.deletePost(this.$route.params.post_id)
        console.log("SUCCESSSSSSSSSS")
    },
    cancelDelete() {
        this.$router.push(`/`)
    }
      
}
}
</script>

<style scoped>
button:focus {
  outline: none;
}
#container {
 display: flex;
 justify-content: center;
 justify-self: center;
 padding: 80px 80px 80px 80px;
 text-align: center;
}
#register {
  color: white;
  background-color: #136a8a;
  background: -webkit-linear-gradient(to right, #B66674, #606D9E);
  background: linear-gradient(to right, #B66674, #606D9E);
  box-shadow: 
    0px 2px 10px rgba(0,0,0,0.2), 
    0px 10px 20px rgba(0,0,0,0.3), 
    0px 30px 60px 1px rgba(0,0,0,0.5);
  padding: 50px 50px 50px 50px;
  border-radius: 10px;
 grid-column-start: 1;
 grid-column-end: col4-end;
 grid-row-start: row1-end;
 grid-row-end: 4;
}
#username {
  margin: 0px 0px 10px 0px;
  background: none;
  text-decoration: none;
  color: white;
  box-shadow: 0px 0px 0px 2px white;
  border-radius: 3px;
  padding: 5px 2em;
  transition: 0.5s;
  outline: none;
  display: flex;
  justify-content: center;
  align-content: center;
  align-items: center;
  justify-self: center;
  align-self: center;
  text-align: center;
}
#password {
  margin: 0px 0px 30px 0px;
  background: none;
  text-decoration: none;
  color: white;
  box-shadow: 0px 0px 0px 2px white;
  border-radius: 3px;
  padding: 5px 2em;
  transition: 0.5s;
  outline: none;
  display: flex;
  justify-content: center;
  align-content: center;
  align-items: center;
  justify-self: center;
  align-self: center;
  text-align: center;
}
.form-signin, .form-control {
  border: none;
  background: none;
  box-shadow: 0px 2px 0px 0px white;
  width: 100%;
  font-size: 1em;
  outline: none;
  text-decoration: none;
}
.form-signin, .form-control::placeholder {
  color: white;
}
#username:hover {
  background-color: #DEDEEF;
  color: #606D9E;
}
#password:hover {
  background-color: #DEDEEF;
  color: #606D9E;
}
#confirmPassword {
  margin: 0px 0px 30px 0px;
  background: none;
  text-decoration: none;
  color: white;
  box-shadow: 0px 0px 0px 2px white;
  border-radius: 3px;
  padding: 5px 2em;
  transition: 0.5s;
  outline: none;
  display: flex;
  justify-content: center;
  align-content: center;
  align-items: center;
  justify-self: center;
  align-self: center;
  text-align: center;
}
#confirmPassword:hover {
  background-color: #DEDEEF;
  color: #606D9E;
}
.button {
  width: 170px;
  padding-top: 30px;
  padding-bottom: 30px;
  text-align: center;
  color: #000;
  text-transform: uppercase;
  font-weight: 600;
  margin-left: 30px;
  margin-bottom: 30px;
  cursor: pointer;
}
#create-account {
  padding: 10px;
  margin: 15px;
  background-color: transparent;
  border: 3px solid #DEDEEF;
  border-radius: 50px;
  -webkit-transition: all .15s ease-in-out;
  transition: all .15s ease-in-out;
  color: #DEDEEF;
}
#create-account:hover {
  box-shadow: 0 0 10px 0 #DEDEEF inset, 0 0 20px 2px #DEDEEF;
  border: 3px solid #DEDEEF;
}
</style>