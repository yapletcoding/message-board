<template>
  <div class="forum">
    <forum-list />
  </div>
</template>

<script>
import ForumList from '@/components/ForumList.vue';
// import forumService from ".../services/ForumService";

export default {
  name: 'Forum',
  components: {
    ForumList
  }
}
</script>
<style>
</style>
