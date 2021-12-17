<template>
<div id="container">
  <div id="login" class="text-center">
    <div id="container-2">
    <form class="form-signin" @submit.prevent="login">
      <h1 class="h3 mb-3 font-weight-normal">Please Sign In</h1>
      <div
        class="alert alert-danger"
        role="alert"
        v-if="invalidCredentials"
      >Invalid username and password!</div>
      <div
        class="alert alert-success"
        role="alert"
        v-if="this.$route.query.registration"
      >Thank you for registering, please sign in.</div>
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
      <button type="submit" id="sign-in">Sign in</button>
      <br>
      <router-link :to="{ name: 'register' }" id="need-account">Need an account?</router-link>
      <br>
    </form>
    </div>
  </div>
  </div>
</template>

<script>
import authService from "../services/AuthService";

export default {
  name: "login",
  components: {},
  data() {
    return {
      user: {
        username: "",
        password: ""
      },
      invalidCredentials: false
    };
  },
  methods: {
    login() {
      authService
        .login(this.user)
        .then(response => {
          if (response.status == 200) {
            this.$store.commit("SET_AUTH_TOKEN", response.data.token);
            this.$store.commit("SET_USER", response.data.user);
            this.$router.push("/");
          }
        })
        .catch(error => {
          const response = error.response;

          if (response.status === 401) {
            this.invalidCredentials = true;
          }
        });
    }
  }
};
</script>

<style scoped>
h1 {
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
 justify-items: center;
 text-align: center;
 padding: 20px 0 40px 0;
}

button:focus {
  outline: none;
}
#container {
 display: flex;
 justify-content: center;
 padding: 80px 80px 40px 80px;
}
#login {
  background: -webkit-linear-gradient(to right, #B66674, #606D9E);
  background: linear-gradient(to right, #B66674, #606D9E);
  box-shadow: 
    0px 2px 10px rgba(0,0,0,0.2), 
    0px 10px 20px rgba(0,0,0,0.3), 
    0px 30px 60px 1px rgba(0,0,0,0.5);
  padding: 2px 50px 50px 50px;
  border-radius: 10px;
 display: grid;
 justify-items: center;
 grid-column-start: 1;
 grid-column-end: col4-end;
 grid-row-start: row1;
 grid-row-end: 4;
 text-align: center;
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
.form-signin, .form-control {
  border: none;
  background: none;
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
#register-button {
 border: none;
  background: none;
  box-shadow: 0px 2px 0px 0px white;
  width: 100%;
  font-size: 1em;
  outline: none;
  text-decoration: none;
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
#sign-in {
  padding: 10px;
  margin: 15px;
  background-color: transparent;
  border: 3px solid #DEDEEF;
  border-radius: 50px;
  -webkit-transition: all .15s ease-in-out;
  transition: all .15s ease-in-out;
  color: #DEDEEF;
}
#sign-in:hover {
  box-shadow: 0 0 10px 0 #DEDEEF inset, 0 0 20px 2px #DEDEEF;
  border: 3px solid #DEDEEF;
}

</style>