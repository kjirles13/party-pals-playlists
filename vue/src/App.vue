<template>
  <nav>
    <div id="app">
      <header>
        <a href="/">
        <img src="./images\DJ Performance Illustration Logo (11).png" alt="Header Image">
        </a>
        <p1 class="p1" style="align: center;">Party Pals Playlist</p1>
      </header>
      <div id="MainView">
      <navigation class="navbar"/>
      <router-view class="main"/>
      </div>
      <head>
        <title></title>
      </head>
    </div>
  </nav>
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

<style>

#MainView {
  display: flex;
  position: relative;
  font-family: sans-serif;
  width: 100%;
  min-height: 100vh;
  background: radial-gradient(#e2d7bd, #aed09e);
}
.p1{
  display:flex;
  position: absolute;
  text-align: center;
  margin-left: 150px;
}
.main {
  flex-grow: 1;
  margin-left: 115px;
  margin-top: 80px;
  
}
.title {
  flex-grow: 1;
  margin-left: 100px;
}
.navbar {
  position: fixed;
  width: 100px;
  z-index: 0;
}
header {
  display: flex;
  position: fixed;
  align-items: center;
  height: 80px;
  width: 100%;
  background-color: #59a985;
  text-align: center;
  top: 0px;
  z-index: 1; 
}
p1{
  font-family:Impact, Haettenschweiler, 'Arial Narrow Bold', sans-serif;
  font-size:x-large;
}
img {
  margin-right: 10px;
  max-width: 150px;
}
/* .angry-grid {
  display: grid;
  grid-template-rows: 1fr 1fr 1fr;
  grid-template-columns: 1fr 1fr 1fr 1fr 1fr;
  gap: 0px;
  height: 100vh;
}
#item-0 { 
  background-color: #9EE7A6; 
  grid-row-start: 1; 
  grid-column-start: 1; 
  grid-row-end: 4; 
  grid-column-end: 2; 
} 
#item-1 {
  background-color: #6A9CC7; 
  grid-row-start: 1; 
  grid-column-start: 2; 
  grid-row-end: 4; 
  grid-column-end: 5; 
} 
#item-2 {
  background-color: #EDA556; 
  grid-row-start: 1; 
  grid-column-start: 5; 
  grid-row-end: 4; 
  grid-column-end: 6;
} */
</style>