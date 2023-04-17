<template>
    <div id="app">
      <header>
        <a href="/">
        <img src="./images\DJ Performance Illustration Logo (11).png" alt="Header Image"></a>
        <h1 class="p1" style="align: center;">Party Playlist Pal</h1>
      </header>
      <div id="MainView">
        <navigation class="navbar"/>
        <router-view class="main"/>
      </div>
      <head>
        <title/>
      </head>
    </div>
</template>

<script>
import Navigation from './views/Navigation.vue'

export default {
  name: 'App',
  components: {
    Navigation,
  }
}
</script>

<style scoped>
#MainView {
  display: flex;
  flex-grow: 2;
  position: relative;
  font-family: sans-serif;
  width: 100%;
  height: 100%;
background: radial-gradient(#b8ccb2, #f6f8f5);}
.p1{
  display:flex;
  position: absolute;
  text-align: center;
  margin-left: 150px;
}
.main {
  flex-grow: 1;
  margin-left: 115px;
  margin-top: 100px;
}
.title {
  flex-grow: 1;
  margin-left: 100px;
}
.navbar {
  position: fixed;
  width: 100px !important;
  z-index: 0;
}
header {
  display: flex;
  position: fixed;
  align-items: center;
  height: 100px;
  width: 100%;
  background-color: #f3d2bb;
  text-align: center;
  top: 0px;
  z-index: 1; 
}
.p1{
  font-family: Impact, Haettenschweiler, 'Arial Narrow Bold', sans-serif;
  font-size:x-large;
}
img {
  margin-right: 10px;
  max-width: 150px;
}
</style>