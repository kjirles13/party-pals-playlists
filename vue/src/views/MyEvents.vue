<template>
  <div class="Events">
    <h1>My Events</h1>
    <events/>

    <h1>My Songs</h1>
    <div class="Songs">
    <div v-for="song in songs" v-bind:key="song.id">
      <songs v-bind:song="song" />
    </div>

  </div>
  

</div>
</template>

<script>
import events from '../components/EventList.vue';
import songs from "../components/SongDisplay.vue";
import songService from "../services/SongService";

export default {
  name: "MyEvents",
  
 components: {
    songs,
    events,
  },
  mounted() {
    document.title = "Events";
  },
  data() {
    return {
      songs: [],
    };
  },
  created() {
    songService.getSongs(this.$store.state.user.username).then((response) => {
      if (response.status == 200) {
        this.songs = response.data;
      }
    });
  },
};

</script>

<style>
</style>