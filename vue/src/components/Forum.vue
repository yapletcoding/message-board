<template>
     <div id="forum">

        <div id="fi1">
    <router-link
    v-bind:to="{ name: 'home'}">
    <button v-on:click="updateFavorite" type="submit"><i class="fas fa-heart" v-bind:class="[isFavorite ? 'red' : '']" @click.prevent="isFavorite = !isFavorite"></i></button>
    </router-link>
<router-link 
v-bind:to="{ name: 'forum-page', params: { forum_id: forum.forum_id}}">
{{forum.forum_title}}
</router-link>
<p>{{forum.forum_description}}</p>
</div>
    </div>
</template>

<script>
import forumService from "@/services/ForumService.js";
export default {
name: 'forum-display',
props: {forum : Object, refresh : Function},
data() {
    return{
        isFavorite: this.forum.favorite, //probably something to do with store
        }
},
methods: {

    updateFavorite() {
        forumService.favoriteForum(this.forum.forum_id, this.forum)
        this.$emit('refresh')
    },
}
}
</script>

<style scoped>

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
    background-color: white;
    padding: 60px 30px 30px 30px;
    border-radius: 12px;
    width: 450px;
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
    background-color: #949CCC;
    border-bottom: 3px solid white;
    border-left: 3px solid white;
    border-right: #B66674;
    border-top: 3px solid #B66674;
    border-radius: 5px;
    padding: 5px 5px 5px 5px;
    width: 400px;
    text-transform: uppercase;
    background: -webkit-linear-gradient(to right, #B66674, #606D9E);
    background: linear-gradient(to right, #B66674, #606D9E);
}
#fi1> a:hover {
    color: white;
    text-shadow: 0 0 5px #ffee10;
    transform: scale(1.1);
}

.red {
    color:red;
}

</style>