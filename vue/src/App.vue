<template>
  <nav>
    <div id="app">
      <header>
        <h1><img src="./images//DJ Performance Illustration Logo (11).png" class="title"/>Party Playlist Pal </h1>
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
  width: 100px;
  border-right-color: black 20px;
  
}
header {
  display: flex;
  position: fixed;
  justify-content: space-between;
  align-items: center;
  height: 80px; 
  padding: 0 20px;
  background-color: rgb(145, 28, 28);
  text-align: center;
  border: solid black 1px;
  top: 0px;

}
img {
  margin-right: 10px;
  max-width: 150px;
}

/* .navigation {
  position: absolute;
  grid-column: 1/2;
} */
/* .content {
  margin-left: 200px;
  grid-column: 2/3;
} */
/* .item-0 {
  grid-column: 1/2 !important;
} */
/* nav-bar {
  grid-column: 1/2;
  position: fixed;
  background-color: #F2F2F2;
  
} */
/* ul {
  list-style: none;
  padding: 0;
  margin: 0;
} */
/* li {
  padding: 10px;
} */
/* a {
  text-decoration: none;
  color: #333;
} */
/* a:hover {
  color: rgb(231, 219, 219);
} */
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
/* .grid-container {
  display: grid;
  grid: 1fr,
} */

</style>