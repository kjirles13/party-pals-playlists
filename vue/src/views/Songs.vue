<template>
  <div class="Songs">
    <songs/>
    <!-- <ul>
      <li v-for="song in songs" :key="song.id"> {{ song.title }}</li>
    </ul> -->
</div>
</template>

<script>
import axios from 'axios';
import songs from '../components/SongList.vue'
const http = axios.create({
    baseURL: 'http://localhost:9000'
});
export default{
  
  name: 'Songs',
  components: {
    songs,
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
