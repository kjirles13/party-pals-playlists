<template>
  <div class="Songs">
    <h1>Songs</h1>
    <ul>
      <li v-for="song in songs" :key="song.id"> {{ song.title }}</li>
      <li><songs/></li>
    </ul>
    <p>Songs Listed here</p>
    <test/>
  

</div>
</template>

<script>
import axios from 'axios';
import Songs from './views/Songs.vue'
const http = axios.create({
    baseURL: 'http://localhost:9000'
});
export default{
  
  name: 'Songs',
  components: {
    Songs,
  },
  
  data() {
    return {
      songs:[]
    }
  },
  
  methods: {
    async getSongs() {
      try{
        const response = await http.get('/songs');
        this.songs = response.data;
      } catch (error) {
        console.log(error);
      }
    }
  },
  mounted() {
    this.getSongs();
  }
   
}
</script>

<style>

</style>
