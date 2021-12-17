<template>
<div class="mine">
<div id="pi1" v-for="post in this.$store.state.myPosts" v-bind:key="post.post_id">
    <router-link 
    v-bind:to="{ name: 'post-page', params: { post_id: post.post_id}}">
    {{post.post_title}}
    </router-link>
    <p>{{post.post_text}}</p>
    </div>
</div>
</template>

<script>
import forumService from "@/services/ForumService.js";
export default {
name: 'my-post',
props: ['post'],
created(){
    this.fetchMyPosts();
},
methods: {
fetchMyPosts() {
      forumService.getMyPosts()
        .then((response) => {
        this.$store.commit("SET_MY_POSTS", response.data)
    })
}
    },
    }
</script>

<style scoped>
#pi1 {
    grid-area: pi1;
    color: #302D4A;
    display: flex;
    flex-direction: column;
    justify-content: flex-start;
    justify-items: center;
    align-items: center;
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
    background-color: white;
    padding: 20px 30px 30px 30px;
    border-radius: 12px;
    width: 450px;
    font-weight: bold;
}
#pi1:hover {
    grid-area: fi1;
    border: 2px solid #302D4A;
    color:white important!;
    background-color: #949CCC;
    font-size: 18px;
    letter-spacing: 6px;
    transition: .25s;
}
#pi1 > a {
    color:#DEDEEF;
    text-shadow: 0 0 3px #B66674;
    transform: scale(1.1);
    background-color: #949CCC;
    border-bottom: 3px solid white;
    border-left: 3px solid white;
    border-right: #B66674;
    border-top: 3px solid #B66674;
    border-radius: 5px;
    padding: 5px 5px 5px 5px;
    width: 410px;
    text-transform: uppercase;
    background: -webkit-linear-gradient(to right, #B66674, #606D9E);
    background: linear-gradient(to right, #B66674, #606D9E);
} 
#pi1 > a:hover {
    color: white;
    text-shadow: 0 0 5px #ffee10;
    transform: scale(1.1);
}

</style>