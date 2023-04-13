<template>
  <div class="Playlists">
    <h1>Playlists</h1>
    <ul>
      <li v-for="playlist in playlists" :key="playlist.id">
        <router-link :to="{ name: 'Playlist', params: { id: playlist.id}}">
          {{ playlist.name }}
        </router-link>
        <playlist/>
      </li>
    </ul>
  </div>
</template>


<script> 

import playlist from '../components/Playlist.vue'

export default {
  name: "Playlist",
 components: {
    playlist,
  },
  
  data() {
    return {
      playlist:[]
    }
  },
  
  mounted() {
    this.getAllSongs();
  }
};
</script>