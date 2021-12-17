<template>
  <div id="container">
  <div id="register" class="text-center">
    <form class="form-register" @submit.prevent="register">
      <h1 class="h3 mb-3 font-weight-normal">Create Account</h1>
      <div class="alert alert-danger" role="alert" v-if="registrationErrors">
        {{ registrationErrorMsg }}
      </div>
      <label for="username" class="sr-only"></label>
      <input
        type="text"
        id="username"
        class="form-control"
        placeholder="Username"
        v-model="user.username"
        required
        autofocus
      />
      <br>
      <label for="password" class="sr-only"></label>
      <input
        type="password"
        id="password"
        class="form-control"
        placeholder="Password"
        v-model="user.password"
        required
      />
      <div id="confirm-password">
      <input
        type="password"
        id="confirmPassword"
        class="form-control"
        placeholder="Confirm Password"
        v-model="user.confirmPassword"
        required
      />
      </div>
      <button class="btn btn-lg btn-primary btn-block" type="submit" id="create-account">
        Create Account
      </button>
      <br>
      <router-link :to="{ name: 'login' }">Have an account?</router-link>
      <br>
    </form>
  </div>
  </div>
</template>

<script>
import authService from '../services/AuthService';

export default {
  name: 'register',
  data() {
    return {
      user: {
        username: '',
        password: '',
        confirmPassword: '',
        role: 'user',
      },
      registrationErrors: false,
      registrationErrorMsg: 'There were problems registering this user.',
    };
  },
  methods: {
    register() {
      if (this.user.password != this.user.confirmPassword) {
        this.registrationErrors = true;
        this.registrationErrorMsg = 'Password & Confirm Password do not match.';
      } else {
        authService
          .register(this.user)
          .then((response) => {
            if (response.status == 201) {
              this.$router.push({
                path: '/login',
                query: { registration: 'success' },
              });
            }
          })
          .catch((error) => {
            const response = error.response;
            this.registrationErrors = true;
            if (response.status === 400) {
              this.registrationErrorMsg = 'Bad Request: Validation Errors';
            }
          });
      }
    },
    clearErrors() {
      this.registrationErrors = false;
      this.registrationErrorMsg = 'There were problems registering this user.';
    },
  },
};
</script>

<style scoped>
button:focus {
  outline: none;
}
#container {
 display: flex;
 justify-content: center;
 justify-self: center;
 padding: 80px 80px 80px 80px;
 text-align: center;
}
#register {
  color: white;
  background-color: #136a8a;
  background: -webkit-linear-gradient(to right, #B66674, #606D9E);
  background: linear-gradient(to right, #B66674, #606D9E);
  box-shadow: 
    0px 2px 10px rgba(0,0,0,0.2), 
    0px 10px 20px rgba(0,0,0,0.3), 
    0px 30px 60px 1px rgba(0,0,0,0.5);
  padding: 50px 50px 50px 50px;
  border-radius: 10px;
 grid-column-start: 1;
 grid-column-end: col4-end;
 grid-row-start: row1-end;
 grid-row-end: 4;
}
#username {
  margin: 0px 0px 10px 0px;
  background: none;
  text-decoration: none;
  color: white;
  box-shadow: 0px 0px 0px 2px white;
  border-radius: 3px;
  padding: 5px 2em;
  transition: 0.5s;
  outline: none;
  display: flex;
  justify-content: center;
  align-content: center;
  align-items: center;
  justify-self: center;
  align-self: center;
  text-align: center;
}
#password {
  margin: 0px 0px 30px 0px;
  background: none;
  text-decoration: none;
  color: white;
  box-shadow: 0px 0px 0px 2px white;
  border-radius: 3px;
  padding: 5px 2em;
  transition: 0.5s;
  outline: none;
  display: flex;
  justify-content: center;
  align-content: center;
  align-items: center;
  justify-self: center;
  align-self: center;
  text-align: center;
}
.form-signin, .form-control {
  border: none;
  background: none;
  box-shadow: 0px 2px 0px 0px white;
  width: 100%;
  font-size: 1em;
  outline: none;
  text-decoration: none;
}
.form-signin, .form-control::placeholder {
  color: white;
}
#username:hover {
  background-color: #DEDEEF;
  color: #606D9E;
}
#password:hover {
  background-color: #DEDEEF;
  color: #606D9E;
}
#confirmPassword {
  margin: 0px 0px 30px 0px;
  background: none;
  text-decoration: none;
  color: white;
  box-shadow: 0px 0px 0px 2px white;
  border-radius: 3px;
  padding: 5px 2em;
  transition: 0.5s;
  outline: none;
  display: flex;
  justify-content: center;
  align-content: center;
  align-items: center;
  justify-self: center;
  align-self: center;
  text-align: center;
}
#confirmPassword:hover {
  background-color: #DEDEEF;
  color: #606D9E;
}
.button {
  width: 170px;
  padding-top: 30px;
  padding-bottom: 30px;
  text-align: center;
  color: #000;
  text-transform: uppercase;
  font-weight: 600;
  margin-left: 30px;
  margin-bottom: 30px;
  cursor: pointer;
}
#create-account {
  padding: 10px;
  margin: 15px;
  background-color: transparent;
  border: 3px solid #DEDEEF;
  border-radius: 50px;
  -webkit-transition: all .15s ease-in-out;
  transition: all .15s ease-in-out;
  color: #DEDEEF;
}
#create-account:hover {
  box-shadow: 0 0 10px 0 #DEDEEF inset, 0 0 20px 2px #DEDEEF;
  border: 3px solid #DEDEEF;
}
</style>
