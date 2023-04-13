<template>
  <div class="Songs">
    <songs/>
    <!-- <ul>
      <li v-for="song in songs" :key="song.id"> {{ song.title }}</li>
    </ul> -->
</div>
</template>

<script>
import songs from '../components/SongList.vue'

export default{
  
  name: 'Songs',
  components: {
    songs
  },
  
  data() {
    return {
      songs:[]
    }
  },
  
  methods: {
  },  
}
</script>

<style>

</style>
