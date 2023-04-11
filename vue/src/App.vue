
<template>
<nav>
  <div id="app">
    <header>
      <h1>Party Playlist Pal</h1>
    </header>
    <div id="nav">
      <navigation></navigation>
      
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
export default {
  name: 'App',
  components: {
    Navigation,
   
  }

  
}
</script>
<style>

.content {
  margin-left: 200px;
 
}
nav-bar {
  position: fixed;
  top: 0;
  left: 0;
  bottom: 0;
  width: 200px;
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
  color: #666;
}
</style>