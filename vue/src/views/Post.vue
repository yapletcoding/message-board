<template>
  <div class="post">
    <post-list />
  </div>
</template>

<script>
import PostList from '@/components/PostList.vue';
// import forumService from ".../services/ForumService";

export default {
  name: 'Post',
  components: {
    PostList
  }
}
</script>