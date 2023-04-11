<template>
<nav class="nav-bar">

  <div>
    <div id="nav">
      <ul>
        <li></li>
        <li><router-link v-bind:to="{ name: 'login' }">Login</router-link></li>
        <li><router-link v-bind:to="{ name: 'home' }">Home</router-link></li>
        <li><router-link v-bind:to="{ name: 'events' }">Events</router-link ></li>
        <li><router-link v-bind:to="{ name: 'playlists' }">Playlists</router-link></li>
        <li><router-link v-bind:to="{ name: 'songs' }">Songs</router-link></li>
        <li><router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link ></li>
      </ul>
    </div>
    <div>
      
    </div>
  </div>
  </nav>
</template>
<script>
export default {
  name: "navbar",
  
}



</script>
<style>
.nav-bar {

  position: fixed;
  top: 0;
  left: 0;
  bottom: 0;
  width: 200px;
  background-color: #5e4a4a !important;
}
ul {
  display: flex;
    flex-direction: column;

}
.nav-bar ul {
  display: flex;
  list-style: none;
  padding: 0;
  margin: 0;
}
.nav-bar li {
  padding: 10px;
}
.nav-bar a {
  text-decoration: none;
  color: rgb(5, 0, 0);
}
.nav-bar a:hover {
  color: rgb(49, 28, 28);
}
</style>






