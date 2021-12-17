<template>
  <div id="app">
    <div id="nav">
      <div id="left">
      <div id="home-button">
      <router-link v-bind:to="{ name: 'home' }">Home</router-link>&nbsp;&nbsp;
      </div>
      <div id="forum-list-button">
      <router-link v-bind:to="{ name: 'forum' }">Explore Forums</router-link>&nbsp;&nbsp;
      </div>
      <div id="create-forum-button">
        <router-link v-bind:to="{ name: 'new-forum-component'}">Create Forum
        </router-link>
        </div>
      <div id="post-list-button">
      <router-link v-bind:to="{ name: 'post' }">Explore Posts</router-link>&nbsp;&nbsp;
      </div>
      </div>
      <div id= "right">
        <div id="logout-button">
      <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link>
      </div>
      </div>
    </div>
    <router-view />
  </div>
</template>
<style>
#nav{
  display: flex;
  justify-content: space-between;
  text-decoration: none;
  color: white;
}
#left {
  display: flex;
}
#home-button{
  background-color: #B66674;
  margin: 5px;
  padding: 10px;
  border-radius: 5px;
  display: flex;
  align-items: flex-start;
  font-family: Inter, sans-serif;
  color: white;
  text-decoration: none;
}
#home-button:hover {
   box-shadow: 
    0px 2px 10px rgba(0,0,0,0.2), 
    0px 10px 20px rgba(0,0,0,0.3), 
    0px 30px 60px 1px rgba(0,0,0,0.5);
}
#logout-button{
  background-color: #606D9E;
  margin: 5px;
  padding: 10px;
  border-radius: 5px;
  display: flex;
  align-items: flex-end;
  font-family: Inter, sans-serif;
  color: white;
  text-decoration: none;
}
#logout-button:hover {
   box-shadow: 
    0px 2px 10px rgba(0,0,0,0.2), 
    0px 10px 20px rgba(0,0,0,0.3), 
    0px 30px 60px 1px rgba(0,0,0,0.5);
}
#forum-list-button{
  background-color: #606D9E;
  margin: 5px;
  padding: 10px;
  border-radius: 5px;
  display: flex;
  align-items: flex-start;
  font-family: Inter, sans-serif;
  color: white;
  text-decoration: none;
}
#forum-list-button:hover {
   box-shadow: 
    0px 2px 10px rgba(0,0,0,0.2), 
    0px 10px 20px rgba(0,0,0,0.3), 
    0px 30px 60px 1px rgba(0,0,0,0.5);
}
#post-list-button{
  background-color: #B66674;
  margin: 5px;
  padding: 10px;
  border-radius: 5px;
  display: flex;
  align-items: center;
  font-family: Inter, sans-serif;
  color: white;
  text-decoration: none;
}
#post-list-button:hover {
   box-shadow: 
    0px 2px 10px rgba(0,0,0,0.2), 
    0px 10px 20px rgba(0,0,0,0.3), 
    0px 30px 60px 1px rgba(0,0,0,0.5);
}
#create-forum-button {
  outline: none;
  background-color: #B66674;
  margin: 5px;
  padding: 10px;
  border-radius: 5px;
  display: flex;
  align-items: center;
  font-family: Inter, sans-serif;
  color: white;
  text-decoration: none;
}
#create-forum-button:hover {
   box-shadow: 
    0px 2px 10px rgba(0,0,0,0.2), 
    0px 10px 20px rgba(0,0,0,0.3), 
    0px 30px 60px 1px rgba(0,0,0,0.5);
}
a {
  text-decoration: none;
  color: #efeff8;
}
a:hover {
  color: white;
}
</style>