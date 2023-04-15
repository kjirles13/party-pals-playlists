<template>
  <div class="Songs">
    <songs/>
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
  mounted() {
    document.title = "Song List";
  }
}
</script>

<style>

</style>
