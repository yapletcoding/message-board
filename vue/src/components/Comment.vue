<template>
     <div id="comment">
        <div id="comment1" v-bind:key="user.user_id"><i class="fas fa-comments"></i>{{user.username}} : {{comment.comment_text}}</div>
        <div id="reaction-buttons-to-comment">
            <button v-on:click="updateLikes">
                <i class="fas fa-hand-holding-medical"></i>
                <p>{{this.likes}}</p>
                </button>
            <button v-on:click="updateDislikes">
                <i class="fas fa-hands-wash"></i>
                <p>{{this.dislikes}}</p>
                </button>
            <button><i class="far fa-comment"></i></button>
        </div>
    </div>
</template>

<script>
import forumService from "@/services/ForumService.js";
export default {
name: 'comment-display',
props: {comment : Object, refresh : Function},
data() {
    return{
        likes: this.comment.comment_likes,
        dislikes: this.comment.comment_dislikes,
        isLiked: this.comment.comment_interaction_like,
        isDisliked: this.comment.comment_interaction_dislike,
        user: {},
    }
},
created(){
    this.getCommenter()
},
methods: {
    updateLikes(){
        forumService.updateCommentLikes(this.comment.post_id, this.comment.id, this.comment)
        this.$emit('refresh');
    
},   
    updateDislikes(){
        forumService.updateCommentDislikes(this.comment.post_id, this.comment.id, this.comment)
        this.$emit('refresh');
    },
    getCommenter() {
        forumService.commenterName(this.comment.id, this.comment)
        .then((response) => {
            console.log(response.data)
        this.user = response.data
    })
}
}
}


</script>

<style scoped>
.fa-comments {
    margin: 0 5px 0 0;
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

  button {
  margin: 0 0 5px 5px;
  color: #606D9E;
  font-weight: 600;
  cursor: pointer;
  border-radius: 5px;
  width: 30%;
  height: 30%;
  display: flex;
  justify-content: center;
  align-items: center;
  align-content: center;
}
#reaction-buttons-to-comment > button:hover {
     color: white;
     background-color:#606D9E;
     text-shadow: #ffee10;
     border-top: 2px solid white;
     border-left: 2px solid white;
}
#reaction-buttons-to-comment {
    display: flex;
    flex-direction: row;
    height: 5%;
    width: 20%;
    justify-items: center;
    justify-content: center;
    align-content: center;
}
p {
    text-shadow: #ffee10;
}
p:hover {
    color: #ffee10;
}

  </style>