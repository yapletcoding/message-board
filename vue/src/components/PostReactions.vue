<template>
<div class="post-reactions">
<div id="reaction-buttons-to-post">
                            <button v-on:click="updatePostLikes">
                                <i class="fas fa-thumbs-up"></i>
                                <p>{{this.postLikes}}</p>
                                </button>
                            <button v-on:click="updatePostDislikes">
                                <i class="fas fa-thumbs-down"></i>
                                <p>{{this.postDislikes}}</p>
                                </button>
                            <button><i class="far fa-comment"></i></button>
                        </div>
                        </div>

</template>

<script>
import forumService from "@/services/ForumService.js";
export default {
name: 'post-reactions-display',
props: {post : Object, refresh : Function},
data() {
    return{
        postLikes: this.post.post_likes,
        postDislikes: this.post.post_dislikes,
        postIsLiked: this.post.post_interaction_like,
        postIsDisliked: this.post.post_interaction_dislike,
    }
},
methods: {
    updatePostLikes(){
        forumService.updatePostLikes(this.post.post_id, this.post)
        this.$emit('refresh')
},   
    updatePostDislikes(){
        forumService.updatePostDislikes(this.post.post_id, this.post)
        this.$emit('refresh')
    },
}
}
</script>
<style scoped>
#post-reactions.post-reactions {
   align-items: right;
    justify-content: right;
    justify-items: right;
}
 button {
  margin: 0 0 5px 5px;
  color: #606D9E;
  font-weight: 600;
  cursor: pointer;
  border-radius: 5px;
  width: 70%;
  height: 10%;
  text-align: center;
  margin: 5px 5px 5px 5px;
  padding: 5px 5px 5px 5px;
  
}
#reaction-buttons-to-post > button:hover {
    scale: (1.1);
    width: 120%;
}
#reaction-buttons-to-post > a {
    height: 5%;
    width: 20%;
}
#reaction-buttons-to-post {
    display: flex;
    flex-direction: row;
    margin: 5px 5px 5px 5px;
    padding: 5px 5px 5px 5px;
    height:fit-content;
    width: fit-content;
}
p {
     margin: 5px 5px 5px 5px;
    padding: 5px 5px 5px 5px;
    color: #949CCC;
    text-decoration: none;
}
</style>