<template>
    <div class="forum-page">
        <ForumPage :key="componentKey"/>
        <!-- <button @click="refreshComponent">Test</button> -->
    </div>
</template>

<script>
import ForumPage from '@/components/ForumPage';

export default {
    name: 'forum-page',
    component: {
        ForumPage
    },
    data() {
        return {
            componentKey: 0
        }
    },
    methods: {
        refreshComponent(){
            this.componentKey += 1
        }
    },
    watch: {
        '$route.params.forum_id' :{
            handler: function() {
            this.refreshComponent()
            }
        }
    }
    
};
</script>