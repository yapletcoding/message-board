<template>
  <div>
      <p v-if="isLoading">Loading...</p>
      <p v-else>Message: {{message.text}} recieved on {{message.date}}</p>
  </div>
</template>

<script>
import MessageService from '../services/MessageService';
export default {
    name: 'message',
    data() {
        return {
            message: {
                text: '',
                date: ''
            },
            isLoading: true
        }
    },
    created() {
        MessageService.getMessage()
            .then( response => {
                this.message = response.data;
                this.isLoading = false;
            })
            .catch( error => {
                console.log('Oops... An error occured: ', error);
                this.isLoading = false;
            })
    }
}
</script>

<style>

</style>