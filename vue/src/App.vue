<template>
  <nav>
    <div id="app">
      <header>
        <h1>Party Playlist Pal</h1>
      </header>
      <div id="nav" class="angry-grid">
        <div class="navigation">
        </div>
        <div id="item-0" class="angry-grid">&nbsp; <navigation/></div> 
        <div id="item-1">&nbsp; <test/></div> 
        <div id="item-2">&nbsp; </div> 
      </div>
      <router-view />
      <head>
        <title></title>
      </head>
    </div>
  </nav>
</template>

<script>

import Navigation from './views/Navigation.vue'
import Test from './components/Test.vue'
export default {
  name: 'App',
  components: {
    Test,
    Navigation,
    
  }
}
</script>

<style>
.button{
  
}
.navigation {
  position: absolute;
  grid-column: 1/2;
}
.content {
  margin-left: 200px;
  grid-column: 2/3;
}
.item-0 {
  grid-column: 1/2 !important;
}
nav-bar {
  grid-column: 1/2;
  position: fixed;
  background-color: #F2F2F2;
}
ul {
  list-style: none;
  padding: 0;
  margin: 0;
}
li {
  padding: 10px;
}
a {
  text-decoration: none;
  color: #333;
}
a:hover {
  color: rgb(109, 10, 10);
}
.angry-grid {
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
}
.grid-container {
  display: grid;
  grid: 1fr,
}

</style>