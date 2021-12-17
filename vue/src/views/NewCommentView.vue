<template>
    <div class="new-comments">
        <NewComment/>
    </div>
</template>

<script>
import NewComment from '@/components/NewComment';

export default {
    name: 'new-comment-component',
    component: {
        NewComment
    }
    
};
</script>
