<template>
  <div id="container" class="new-forum">
  <div id="register" class="text-center">
    <form class="form-register" @submit.prevent="createForum">
      <h1 class="h3 mb-3 font-weight-normal">Create Forum</h1>
      <label for="username" class="sr-only"></label>
      <input
        type="text"
        id="username"
        class="form-control"
        placeholder="enter forum name"
        v-model="forum.forum_title"
        required
      />
      <br>
      <label for="password" class="sr-only"></label>
      <input
        type="text"
        id="password"
        class="form-control"
        placeholder="enter forum description"
        v-model="forum.forum_description"
        required
      />
      <button class="btn btn-lg btn-primary btn-block" type="submit" id="create-account" v-on:submit.prevent="createForum()">
        create your forum!
      </button>
    </form>
  </div>
  </div>
</template>

<script>
import forumService from "@/services/ForumService.js";

export default {
  name: 'create-post',
  data() {
    return {
      forum: {
        forum_title: "",
        forum_description: "",
      },
    };
  },
  methods: {
      createForum() {
          const newForum = {
              forum_title: this.forum.forum_title,
              forum_description: this.forum.forum_description,
          }
      
        forumService
          .postForum(newForum)
          .then((response) => {
            this.newForum = response.data;
            this.$router.push(`/forum`);
          })
        
      },
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
  border-bottom: 0px 0px 0px 2px white;
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
  cursor: pointer;
}
#password {
  margin: 0px 0px 30px 0px;
  background: none;
  text-decoration: none;
  color: white;
  border-bottom: 0px 0px 0px 2px white;
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
  cursor: pointer;
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
  border-bottom: 0px 0px 0px 2px white;
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
  cursor: pointer;
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
  cursor: pointer;
}
#create-account:hover {
  box-shadow: 0 0 10px 0 #DEDEEF inset, 0 0 20px 2px #DEDEEF;
  border: 3px solid #DEDEEF;
}
</style>