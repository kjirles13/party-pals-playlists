<template>
  <div class="Songs">
    <div v-for="song in songs" v-bind:key="song.id">
      <songs v-bind:song="song" />
    </div>
  </div>
</template>

<script>
import songs from "../components/SongDisplay.vue";
import songService from "../services/SongService";

export default {
  name: "Songs",
  components: {
    songs,
  },
  data() {
    return {
      songs: [],
    };
  },
  created() {
    songService.getSongs().then((response) => {
      if (response.status == 200) {
        this.songs = response.data;
      }
    });
  },
};
</script>

<style>
</style>
