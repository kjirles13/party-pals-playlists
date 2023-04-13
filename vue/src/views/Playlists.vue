<template>
  <div class="Playlists">
    <h1>Playlists</h1>
    <ul>
      <li v-for="playlist in playlists" :key="playlist.id">
        <router-link :to="{ name: 'Playlist', params: { id: playlist.id}}">
          {{ playlist.name }}
        </router-link>
        
      </li>
    </ul>
  </div>
</template>


<script> 
import axios from 'axios';

export default {
  name: 'Playlists',
  data() {
    return {
      playlists: [],
    };
  },
  created() {
    axios.get('http://localhost:9000/playlists')
    .then(response => {
      this.playlists = response.data;
    })
    .catch(error => {
      console.log(error);
    });
  },
};
</script>